import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

/**
 * This class contains methods to return random numbers as generated by <a href="https://api.random.org/json-rpc/4/basic">Random.org's </a> atmospheric noise randomness generator.
 * This makes the random numbers in the casino not pseudorandom and removes the option for someone to break the casino by figuring out the pattern for random numbers.
 * The class is built around the concept that we can the api once and get a lot of numbers which we can store. This is easier than calling the API everytime
 * we need a die roll or a card pick or a roulette spin because it means that we don't need to connect
 * to the internet mid-game, and it means we can minimize the number of API calls since if we make too many, it will cost money.
 * @author adhi-thirumala
 */
public class RandomOrgGenerator {
    /**
     * Stack containing up to 10k integers that can either be dice rolls or roulette spins, the numbers range from 0, 185 meaning that when u call modulo 6 on them, it's like a die roll, and when you call modulo 38, it's like a roulette spin.
     */
    private static final ReferenceStack<Integer> rouletteDiceIntegers = new ReferenceStack<>();
    /**
     * Stack containing up to 10k integers from -1e9, to 1e9 (the max range that RandomOrg can generate). these serve as seeds for the pseudorandom generator that shuffles a deck of cards.
     */
    private static final ReferenceStack<Integer> cardDeckShuffleSeeds = new ReferenceStack<>();

    private static boolean hasInitialized = false;

    /**
     * The method refreshes both of the above fields once. If the method has already been called, it does nothing.
     */
    public static void initialize() {
        if(hasInitialized)
            return;
        refreshCardSeeds();
        refreshRouletteDice();
        hasInitialized = true;
    }

    /**
     * This method makes an API call to the <a href="https://api.random.org/json-rpc/4/basic">Random.org </a> HTML JSON-RPC2 API.
     * Documentation for the full JSON request format is in the above link, but we get the array that contains the random numbers that are generated.
     * The method generates 10k numbers (the max in one request) because that is what all methods currently use.
     * @param min The minimum bound (inclusive) for the random generation.
     * @param max The maximum bound (inclusive) for the random generation.
     * @return JsonArray containing the random numbers that were generated.
     */
    private static JsonArray makeAPICall(int min, int max) {
        //chatgpt wrote the beginning of this, i wrote the part
        // that takes the response string that the HTML request gives and
        // turns it into a json array, parts of it are in this and parts
        // are in the methods that this called. ***note that the api key in this request has been cancelled and you can't use it.
        //prompt: how do i make https requests with jsonrpd2 in java, then, i have this json {
        //    "jsonrpc": "2.0",
        //    "method": "generateIntegers",
        //    "params": {
        //        "apiKey": "0e07bf93-969e-41ed-9b5f-8d339266dcd6",
        //        "n": 10,
        //        "min": 1,
        //        "max": 10,
        //        "replacement": true,
        //        "base": 10,
        //        "pregeneratedRandomization": null
        //    },
        //    "id": 12713
        //}
        //then, https://api.random.org/json-rpc/4/invoke
        //then, turn the string that is the response into a json itself
        // open connection to server url
        HttpURLConnection huc = startRandomOrgConnection();

        // Create the JSON-RPC request - gpt used a hashmap, apparently this is the best way to generate JSONs.
        Map<String, Object> request = new HashMap<>();
        request.put("jsonrpc", "2.0");
        request.put("method", "generateIntegers");
        request.put("id", 12713);

        Map<String, Object> params = new HashMap<>();
        params.put("apiKey", System.getenv("APIKEYRANDOMORG")); //need env variable in configuration
        params.put("n", 10000); //pick max numbers
        params.put("min", min);
        params.put("max", max);
        request.put("params", params);

        try {
            // Convert the request map to JSON
            String jsonRequest = new Gson().toJson(request);
            // Send the request
            OutputStream os;
            if (huc != null) {
                os = huc.getOutputStream();
                os.write(jsonRequest.getBytes());
                os.flush();
            }
            // Read the response
            BufferedReader in = null;
            if (huc != null) {
                in = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            }
            String inputLine;
            StringBuilder responseString = new StringBuilder();
            if (in != null) {
                while ((inputLine = in.readLine()) != null)
                    responseString.append(inputLine);
                in.close();
            }
            // turn the response string back into a json, so we can grab the array of outputs = https://www.baeldung.com/gson-string-to-jsonobject
            JsonObject responseJson = JsonParser.parseString(responseString.toString()).getAsJsonObject();
            JsonObject randomJson = responseJson.get("random").getAsJsonObject();
            return randomJson.getAsJsonArray("data");
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Starts the HTTP connection to <a href="https://api.random.org/json-rpc/4/basic">Random.org</a>
     * @return the HTTPURLConnection Object
     */
    private static HttpURLConnection startRandomOrgConnection() {
        try {
            URL url = new URI("https://api.random.org/json-rpc/4/invoke").toURL();
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            // Configure the connection
            huc.setDoOutput(true);
            huc.setDoInput(true);
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Content-Type", "application/json");
            return huc;
        }
        catch (URISyntaxException | IOException e) {//only time this happens if connection fails i think?
            System.out.println(e);
        }
        return null;
    }

    /**
     * Fills the stack with integers that are roulette spins and dice rolls.
     */
    private static void refreshRouletteDice() {
        JsonArray rouletteDiceArray = makeAPICall(0, 185); //allows us to modulo both 6 (technically 0-5) (number of dice) and 38 (number of roulette spots) (0-37) because 38 = lcm(5, 37).
        if (rouletteDiceArray != null) {
            for (int i = 0; i < rouletteDiceArray.size(); i++)
                rouletteDiceIntegers.push(rouletteDiceArray.get(i).getAsInt());
        }
    }

    /**
     * Fills the stack with seeds for the card shuffling.
     */
    private static void refreshCardSeeds() {
        JsonArray seeds = makeAPICall((int) (-1 * Math.pow(10, 9)), (int) (Math.pow(10, 9))); //big seeds, little seeds
        if (seeds != null) {
            for (int i = 0; i < seeds.size(); i++)
                cardDeckShuffleSeeds.push(seeds.get(i).getAsInt());
        }
    }

    /**
     * Spins the roulette wheel. Note that 00 is actually 37.
     * @return the number that is spun from 0 to 37.
     */
    public static int spinRouletteWheel() {
        int result = rouletteDiceIntegers.pop() % 38; //00 is actually 37
        if (rouletteDiceIntegers.isEmpty())
            refreshRouletteDice();
        return result;
    }

    /**
     * Pops a value from the stack of deck shuffle seeds. Refreshes the stack
     * if it gets empty.
     * @return the seed to be used.
     */
    public static int getDeckShuffleSeed() {
        int result = cardDeckShuffleSeeds.pop();
        if (cardDeckShuffleSeeds.isEmpty())
            refreshCardSeeds();
        return result;
    }

    /**
     * Returns the value of one die roll. Refills the stack
     * if it gets empty.
     * @return a value from 1 to 6.
     */
    public static int rollDie() {
        int result = rouletteDiceIntegers.pop() % 6 + 1;
        if (rouletteDiceIntegers.isEmpty())
            refreshRouletteDice();
        return result;
    }

    /**
     * Returns the sum total of multiple dice rolls.
     * @param n the number of dice to be rolled.
     * @return the sum of the dice rolls.
     */
    public static int rollDice(int n) {
        int result = 0;
        for (int i = 0; i < n; i++)
            result += rollDie();
        return result;
    }

}
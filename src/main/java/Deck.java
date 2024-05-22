import java.util.Random;

/**
 * Implements a deck of cards. Functionally an array
 * based {@link Stack} with only the ability to draw a
 * card (popping).
 * Drawing a card never removes it from the array
 * which means that we never have to change the
 * contents of the array post initialization, just shuffle it.
 * @author adhi-thirumala
 */

public class Deck {
    /**
     * Array of {@link Card}s that make up the deck.
     */
    private final Card[] cardArr;

    /**
     * Integer representing the index of the value that is
     */
    private int top;

    /**
     * Constructor for the deck. Initializes the array with every card object and shuffles it.
     * @param deckCount the amount of decks to be shuffled together.
     */
    public Deck(int deckCount){
        cardArr = new Card[deckCount * 52];
        top = 0;
        for(int i = 0; i < cardArr.length; i++){
            cardArr[i] = new Card(i);
        }
        shuffle();
    }

    /**
     * Constructor for the deck. Initializes the array with every card object and shuffles it. Contains only one deck (52) worth of cards.
     */
    public Deck(){
        cardArr = new Card[52];
        top = 0;
        for(int i = 0; i < cardArr.length; i++){
            cardArr[i] = new Card(i);
        }
        shuffle();
    }

    /**
     * Draws one card from the array, and moves the top value
     * one forward. If the card drawn is the last one, the deck shuffles and the top returns to the beginning of the array.
     * @return the {@link Card} at the top of the deck.
     */
    public Card draw(){
        Card result = cardArr[top];
        top++;
        if(top > cardArr.length - 1) {
            shuffle();
            top = 0;
        }
        return result;
    }

    /**
     * Utilizes the modern version of the Fisher-Yates shuffling algorithm created by Richard Durstenfeld. Initializes the random number generator with a random seed from {@link RandomOrgGenerator}. <p> </p>
     * Algorithm source: <p></p>
     * Durstenfeld, R. (July 1964). "Algorithm 235: Random permutation" (PDF). Communications of the ACM. 7 (7): 420. <a href = https://doi:10.1145/364520.364540>https://doi:10.1145/364520.364540</a>. S2CID 494994.
     */

    private void shuffle(){
        Random random = new Random(RandomOrgGenerator.getDeckShuffleSeed());
        for(int i = cardArr.length - 1; i > 0; i--){
            int j = random.nextInt(0, i);
            Card temp = cardArr[j];
            cardArr[j] = cardArr[i];
            cardArr[i] = temp;
        }
    }
}


/**
 * Plays the game of baccarat. Plays the Punto Banco variant. Only one player, they can bet on either the "player" hand or the "Banker" hand. All
 * card drawings are randomized by <a href = https://www.api.random.org>Random.org</a>'s API as implemented in {@link RandomOrgGenerator}.
 * @author adhi-thirumala
 */
public class Baccarat {
    private final int playerBet;

    private final Deck deck;

    public Baccarat(int playerBet){
        this.playerBet = playerBet;
        deck = new Deck(6);
    }







}

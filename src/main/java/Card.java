/**
 * This card implements one card in a deck. It gives access to the suit and the number of the card.
 * in the suit field. in the number field.
 * @author adhi-thirumala
 */
public class Card {

    /**
     * Integer representing the suit of the card.  0 is spades, 1 is hearts, 2 is clubs, and 3 is diamonds.
     */
    private final int suit;
    /**
     * Integer representing the number on the card. 1 is Ace, 11 is Jack, 12 is Queen and 13 is King.
     */
    private final int number;

    /**
     * Initializes one card. Uses a system
     * where zero is the ace of spades, and so on. It ends at the King of Diamonds.
     * @param i the initialization value.
     */
    public Card(int i) {
        i %= 52; //for the multiple decks scenario to work
        suit = i / 13; //0 to 12 is spades and so on.
        number = i % 13 + 1; //add one so that we can have the number conversion as shown above.
    }

    /**
     * Getter for the suit of the card.
     * @return the suit of the card.
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Getter for the number of the card.
     * @return the number on the card.
     */
    public int getNumber() {
        return number;
    }

    public String toString(){
        String temp = "";
        switch(number){
            case 1 -> temp += "Ace";
            case 11 -> temp += "Jack";
            case 12 -> temp += "Queen";
            case 13 -> temp += "King";
            default -> temp += String.valueOf(number);
        }
        switch(suit){
            case 0 -> temp += "♠";
            case 1 -> temp += "❤";
            case 2 -> temp += "♣";
            case 3 -> temp += "♦";
        };

        return temp;
    }

}


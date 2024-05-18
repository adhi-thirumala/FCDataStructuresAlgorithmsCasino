
public class Card {

    private final int suit;
    private final int number;


    public Card(int i) {
        suit = i / 13; //0 to 12 is spades and so on.
        number = i % 13 + 1; //add one so that we can have the number conversion as shown above.
    }

    public int getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }
/* This is presumably useless for now, who knows if it wil be useful later
    private String suitToString(){
        return switch(suit){
            case 0 -> "Spades";
            case 1 -> "Hearts";
            case 2 -> "Clubs";
            case 3 -> "Diamonds";
            default -> throw new IllegalStateException(STR."Unexpected value: \{suit}");
        };
 */
}


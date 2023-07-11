public class Card {
    private int value;
    private String suit;
    public Card(){
        this.value = 0;
        this.suit = null;
    }
    public Card(int value, String suit){
        this.value = value;
        this.suit = suit;
    }
    public Card(Card card){
        this.value = card.value;
        this.suit = card.suit;
    }
    public int getValue(){
        return this.value;
    }
    public String getSuit(){
        return this.suit;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }
    public String toString(){
        switch(this.value){ //Changing 1, 11, 12, 13 to Ace, Jester, Queen, King
            case 1:
                return("Ace of " + this.suit);
            case 11:
                return ("Jester of " + this.suit);
            case 12:
                return ("Queen of " + this.suit);
            case 13:
                return ("King of " + this.suit);
        }
        return (this.value + " of " + this.suit);
    }
    public static Card[] createDeck(){
        Card[] sortedDeck = new Card[52];
        for(int i = 0; i<=12; i++) {
            sortedDeck[i] = new Card(i+1, "\u2665");
            sortedDeck[i+13] = new Card(i+1, "\u2666");
            sortedDeck[i+26] = new Card(i+1, "\u2660");
            sortedDeck[i+39] = new Card(i+1, "\u2663");
        }
        return sortedDeck;
    }
    public static Card[] shuffle(Card[] deck){
        //We know a deck always has 52 cards, a Deck would have indexes 0-51.
        for(int i = 0; i<=51; i++){
            int random = randomNumber();
            //To shuffle, we'll take the first card and switch it with a random one, then the second card, until the end.
            //For that we need a method that generates a random number between 0 and 51.
            Card temp = deck[i];
            deck[i] = deck[random];
            deck[random] = temp;
        }
        return deck;
    }
    private static int randomNumber(){
        return ((int)(Math.random()*52));
    }
}

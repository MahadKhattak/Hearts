import java.util.ArrayList;
public class Player {
    private Card[] deck;
    private ArrayList<Card> takenCards;
    private int points;
    public Player(){
        this.deck = new Card[13];
        this.takenCards = new ArrayList<Card>();
        this.points = 0;
    }
    public Player(Card[] deck, ArrayList<Card> takenCards, int points){
        this.deck = deck; //Maybe use a deep copy?
        this.takenCards = takenCards; //Same thing here?
        this.points = points;
    } //Might be used to save the game later
    //Having a binary file with Players written to it that could be loaded, that'd be nice
    public Card[] getDeck(){
        Card[] deck = new Card[this.deck.length];
        for(int i = 0; i<this.deck.length; i++){
            deck[i] = this.deck[i];
        }
        return this.deck;
    }
    public int getPoints(){
        return this.points;
    }

    public void displayTakenCards(){
        for (int i = 0; i < this.takenCards.size(); i++) {
            System.out.print("|" + this.takenCards.get(i).toString() + "|" + " ");
            if (i == this.takenCards.size() / 2)
                System.out.println();
        }
    }
    public void setDeck(Card[] deck) {
        this.deck = deck;
    }

    public void addToTakenCardsAndPoints(Card[] cards){
        for(int i = 0; i<cards.length; i++){
            this.takenCards.add(cards[i]);
            if(cards[i].getSuit().equals("\u2665"))
                this.points++; //To calculate points
            else if(cards[i].toString().equalsIgnoreCase("Queen of \u2660"))
                this.points += 13;
        }
    }
}

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
        this.deck = deck;
        this.takenCards = takenCards;
        this.points = points;
    } //Might be used to save the game later
      //Having a binary file with Players written to it that could be loaded, that'd be nice
    public Card[] getDeck(){
        return this.deck;
    }

}

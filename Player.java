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
        Card[] deck = new Card[13];
        for(int i = 0; i<=12; i++){
            deck[i] = this.deck[i];
        }
        return deck;
    }

}

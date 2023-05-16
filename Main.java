import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //First let's create a Deck array that stores all the cards needed.
        Scanner keyIn = new Scanner(System.in);
        System.out.println("4 players are needed to play the game.");
        Card[] p1deck = new Card[13];
        Card[] p2deck = new Card[13];
        Card[] p3deck = new Card[13];
        Card[] p4deck = new Card[13];
        Card[] sortedDeck = Card.createDeck();
        Card[] randomized = Card.shuffle(sortedDeck);
        for (int i = 0; i <= 12; i++) {
            p1deck[i] = randomized[i];
            p2deck[i] = randomized[i + 13];
            p3deck[i] = randomized[i + 26];
            p4deck[i] = randomized[i + 39];
        }
        Player p1 = new Player(p1deck, null, 0);
        Player p2 = new Player(p2deck, null, 0);
        Player p3 = new Player(p3deck, null, 0);
        Player p4 = new Player(p4deck, null, 0);
        Player[] players = {p1, p2, p3, p4};
        Methods.printTable();
        int starterPlayerNumber = checkStart(players);
        String firstChoice = keyIn.nextLine();
        while(!firstChoice.equals("2 of Spades")) {
            System.out.println("The first card always has to be the 2 of Spades!");
            firstChoice = keyIn.nextLine();
        }
        players[starterPlayerNumber-1].setDeck(Card.removeCardFromDeck(players[starterPlayerNumber-1].getDeck(), "2 of Spades"));
        Card[] test = players[starterPlayerNumber-1].getDeck();
        for(int i = 0; i<players[starterPlayerNumber-1].getDeck().length; i++)
            System.out.println(test[i]);
        //Put this stuff into a loop, and continue it until the game's over; also add mechanism to remove the cards from the deck.
        System.out.println("Player " + (starterPlayerNumber + 1) + "'s turn");
        boolean gameFinished = false;
        while(!gameFinished) {
            starterPlayerNumber++;
            if (starterPlayerNumber < 4)
                Methods.printDeck(players[starterPlayerNumber].getDeck());
            else
                Methods.printDeck(players[0].getDeck());
        }

    }

    public static int checkStart(Player[] players) {
        for (int i = 0; i <= 3; i++) {
            Card[] currentDeck = players[i].getDeck();
            for (int j = 0; j <= 12; j++)
                if (currentDeck[j].toString().equals("2 of " + "\u2663")) {
                    System.out.println("Player " + (i + 1) + " starts.");
                    Methods.printDeck(currentDeck);
                    return(i+1);
                }
        }
        return 0; //Full disclosure: don't know why this is here.
    }
}

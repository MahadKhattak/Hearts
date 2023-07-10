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
        while(!firstChoice.equalsIgnoreCase("2 of Clubs")) {
            System.out.println("The first card always has to be the 2 of Clubs!");
            firstChoice = keyIn.nextLine();
        }
        Card[] newDeck = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), "2 of " + "\u2663");
        players[starterPlayerNumber].setDeck(newDeck);
        starterPlayerNumber++;
        System.out.println();
        boolean gameFinished = false;
        while(!gameFinished){
            System.out.println("Player " + (starterPlayerNumber + 1) + "'s turn");
            System.out.println();
            if (starterPlayerNumber < 4){
                Methods.printDeck(players[starterPlayerNumber].getDeck());
                playCard(players, starterPlayerNumber);
            }
            else {
                starterPlayerNumber = 0;
                Methods.printDeck(players[0].getDeck());
                playCard(players, starterPlayerNumber);
            }
            starterPlayerNumber++;
            if(players[0].getDeck().length==0&&players[1].getDeck().length==0&&players[2].getDeck().length==0&&players[3].getDeck().length==0){
                System.out.println("Game is over. Good game!");
                gameFinished = true;
            }
        }

    }

    public static int checkStart(Player[] players) {
        for (int i = 0; i <= 3; i++) {
            Card[] currentDeck = players[i].getDeck();
            for (int j = 0; j <= 12; j++)
                if (currentDeck[j].toString().equals("2 of " + "\u2663")) {
                    System.out.println("Player " + (i + 1) + " starts.");
                    Methods.printDeck(currentDeck);
                    return(i);
                }
        }
        return 0; //Full disclosure: don't know why this is here.
    }

    public static void playCard(Player[] players, int starterPlayerNumber){
        Scanner keyIn = new Scanner(System.in);
        System.out.println();
        System.out.println("Select a card to play.");
        String choice1 = keyIn.nextLine();
        //Error handling to check if card is in deck or not
        String choice = convertToASCII(choice1);
        Card[] newDeck2 = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), choice);
        players[starterPlayerNumber].setDeck(newDeck2);
    }

    public static String convertToASCII(String card){
        String s = null;
        if(card.substring(5).equalsIgnoreCase("Hearts"))
            s = card.substring(0, 5) + "\u2665";
        else if(card.substring(5).equalsIgnoreCase("Spades"))
            s = card.substring(0, 5) + "\u2660";
        else if(card.substring(5).equalsIgnoreCase("Clubs"))
            s = card.substring(0, 5) + "\u2663";
        else if(card.substring(5).equalsIgnoreCase("Diamonds"))
            s = card.substring(0, 5) + "\u2666";
        return s;
    }
}

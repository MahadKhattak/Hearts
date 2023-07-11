import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //First let's create a Deck array that stores all the cards needed.
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
        Scanner keyIn = new Scanner(System.in);
        int starterPlayerNumber = checkStart(players);
        String firstChoice = keyIn.nextLine();
        while (!firstChoice.equalsIgnoreCase("2 of Clubs")) {
            System.out.println("The first card always has to be the 2 of Clubs!");
            firstChoice = keyIn.nextLine();
        }
        Card[] newDeck = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), "2 of " + "\u2663");
        players[starterPlayerNumber].setDeck(newDeck);
        if (starterPlayerNumber < 4)
            starterPlayerNumber++;
        else
            starterPlayerNumber = 0;
        System.out.println();
        boolean gameFinished = false;
        boolean firstRound = true;
        while (!gameFinished) {
            boolean firstCard = true;
            int i = 0;
            String[] currentStack = new String[4];
            for (i = 0; i < currentStack.length; i++) {
                if (firstRound) {
                    i = 1;
                    currentStack[0] = "\u2663";
                    firstRound = false;
                }
                if (i >= 1)
                    firstCard = false;
                System.out.println();
                if (starterPlayerNumber >= 4) {
                    starterPlayerNumber = 0;
                }
                System.out.println("Player " + (starterPlayerNumber + 1) + "'s turn");
                currentStack[i] = playCard(players, starterPlayerNumber);
                if (!firstCard) {
                    while (!(currentStack[i].equals(currentStack[i - 1])) && suitInDeck(players[starterPlayerNumber].getDeck(), currentStack[i - 1])) {
                        System.out.println("The card you place must be of the same suit as the previous card placed. Try again.");
                        currentStack[i] = playCard(players, starterPlayerNumber);
                    }
                }
                starterPlayerNumber++;
                if (players[0].getDeck().length == 0 && players[1].getDeck().length == 0 && players[2].getDeck().length == 0 && players[3].getDeck().length == 0) {
                    System.out.println("Game is over. Good game!");
                    gameFinished = true;
                }
            }
        }
        checkStart(players);
    }

    public static int checkStart(Player[] players) {
        for (int i = 0; i <= 3; i++) {
            Card[] currentDeck = players[i].getDeck();
            for (int j = 0; j <= 12; j++)
                if (currentDeck[j].toString().equals("2 of " + "\u2663")) {
                    System.out.println("Player " + (i + 1) + " starts.");
                    Methods.printDeck(currentDeck);
                    return (i);
                }
        }
        return 0;
    }

    //Make inDeck() method that checks if the card is in deck.
    public static String playCard(Player[] players, int starterPlayerNumber) {
        Methods.printDeck(players[starterPlayerNumber].getDeck());
        Scanner keyIn = new Scanner(System.in);
        System.out.println();
        System.out.println("Select a card to play.");
        String choice1 = keyIn.nextLine();
        String[] temp = choice1.split(" ");
        String suit = temp[temp.length - 1];
        String choice = convertToASCII(choice1);
        //Error handling for wrong card
        boolean cardInDeck = false;
        Card[] currentDeck = players[starterPlayerNumber].getDeck();
        for (int i = 0; i < currentDeck.length; i++) {
            if (currentDeck[i].toString().equalsIgnoreCase(choice))
                cardInDeck = true;
        }
        if (cardInDeck) { //Maybe just change the condition here? Returning a suit with the array works too tho
            Card[] newDeck = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), choice);
            players[starterPlayerNumber].setDeck(newDeck);
        } else {
            System.out.println("Card is not in deck. Please choose a card in your deck.");
            playCard(players, starterPlayerNumber);
        }
        return suit; //Return just the suit so that we can compare the suit to other cards
    }

    public static boolean suitInDeck(Card[] currentDeck, String suit) {
        for (int i = 0; i < currentDeck.length; i++) {
            if (currentDeck[i].getSuit().equalsIgnoreCase(suit))
                return true;
        }
        return false;
    }

    public static String convertToASCII(String card) {
        String[] temp = card.split(" ");
        String s = null;
        if (temp[2].equalsIgnoreCase("Hearts"))
            s = temp[0] + " " + temp[1] + " \u2665";
        else if (temp[2].equalsIgnoreCase("Spades"))
            s = temp[0] + " " + temp[1] + " \u2660";
        else if (temp[2].equalsIgnoreCase("Clubs"))
            s = temp[0] + " " + temp[1] + " \u2663";
        else if (temp[2].equalsIgnoreCase("Diamonds"))
            s = temp[0] + " " + temp[1] + " \u2666";
        return s;
    }
}

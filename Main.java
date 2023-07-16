import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

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
        Player p1 = new Player(p1deck, new ArrayList<>(), 0);
        Player p2 = new Player(p2deck, new ArrayList<>(), 0);
        Player p3 = new Player(p3deck, new ArrayList<>(), 0);
        Player p4 = new Player(p4deck, new ArrayList<>(), 0);
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
        Player[] playerStack = new Player[4];
        playerStack[0] = players[starterPlayerNumber];
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
            Card[] currentStack = new Card[4];
            if(!firstRound)
                playerStack = new Player[4];
            for (i = 0; i < currentStack.length; i++) {
                if (starterPlayerNumber >= 4) {
                    starterPlayerNumber = 0;
                }
                if (firstRound) {
                    currentStack[0] = new Card(2, "\u2663");
                    firstRound = false;
                    continue;
                }
                    playerStack[i] = players[starterPlayerNumber];
                if (i >= 1)
                    firstCard = false;
                System.out.println();
                System.out.println("Player " + (starterPlayerNumber + 1) + "'s turn.");
                System.out.println("POINTS: " + players[starterPlayerNumber].getPoints());
                System.out.println("TAKEN CARDS: ");
                players[starterPlayerNumber].displayTakenCards();
                System.out.println();
                if (!firstCard)
                    currentStack[i] = playCard(players, starterPlayerNumber, currentStack[0]);
                else
                    currentStack[i] = playCard(players, starterPlayerNumber);
                starterPlayerNumber++;
            }
            int highestCardIndex = Card.findHighestCardIndex(currentStack);
            playerStack[highestCardIndex].addToTakenCardsAndPoints(currentStack);
            //Loop through, check which player put the highest card, add to their arraylist.
            //Player array with the players in their order of play, that matches them to cards automatically.
            //Then loop through Card array, check which has the biggest value, takes its index and give all the cards in the array to the player.
            //Repeat until end.
            if (players[0].getDeck().length == 0 && players[1].getDeck().length == 0 && players[2].getDeck().length == 0 && players[3].getDeck().length == 0) {
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
                    return (i);
                }
        }
        return 0;
    }

    public static Card playCard(Player[] players, int starterPlayerNumber) {
        boolean validCard = false;
        String[] temp = null;
        while(!validCard){
            Methods.printDeck(players[starterPlayerNumber].getDeck());
            Scanner keyIn = new Scanner(System.in);
            System.out.println();
            System.out.println("Select a card to play.");
            String choice1 = keyIn.nextLine();
            temp = choice1.split(" ");
            if (temp.length != 3 || temp == null) {
                System.out.println("An invalid card was entered. Try again.");
                continue;
            }
            String choice = convertToASCII(choice1);
            if (choice == null) {
                System.out.println("An invalid card was entered. Try again.");
                continue;
            }
            //Error handling for wrong card
            boolean cardInDeck = false;
            Card[] currentDeck = players[starterPlayerNumber].getDeck();
            for (int i = 0; i < currentDeck.length; i++) {
                if (currentDeck[i].toString().equalsIgnoreCase(choice))
                    cardInDeck = true;
            }
            if (cardInDeck) {
                Card[] newDeck = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), choice);
                players[starterPlayerNumber].setDeck(newDeck);
            } else {
                System.out.println("Card is not in deck. Please choose a card in your deck.");
                continue;
            }
            validCard = true;
        }
        return new Card(toInt(temp[0]), temp[2]);
    }

    public static Card playCard(Player[] players, int starterPlayerNumber, Card firstCard) {
        boolean validCard = false;
        String[] temp = null;
        while(!validCard){
            Methods.printDeck(players[starterPlayerNumber].getDeck());
            Scanner keyIn = new Scanner(System.in);
            System.out.println();
            System.out.println("Select a card to play.");
            String choice1 = keyIn.nextLine();
            temp = choice1.split(" ");
            if (temp.length != 3 || temp == null) {
                System.out.println("An invalid card was entered. Try again.");
                continue;
            }
            String choice = convertToASCII(choice1);
            if (choice == null) {
                System.out.println("An invalid card was entered. Try again.");
                continue;
            }
            if(!(choice.substring(choice.length()-1).equalsIgnoreCase(firstCard.getSuit()))&&suitInDeck(players[starterPlayerNumber].getDeck(), firstCard)){
                System.out.println("The card you placed must be of the same suit as the card placed first, unless your deck does not contain the suit.");
                continue;
            }
            //Error handling for wrong card
            boolean cardInDeck = false;
            Card[] currentDeck = players[starterPlayerNumber].getDeck();
            for (int i = 0; i < currentDeck.length; i++) {
                if (currentDeck[i].toString().equalsIgnoreCase(choice))
                    cardInDeck = true;
            }
            if (cardInDeck) {
                Card[] newDeck = Card.removeCardFromDeck(players[starterPlayerNumber].getDeck(), choice);
                players[starterPlayerNumber].setDeck(newDeck);
            } else {
                System.out.println("Card is not in deck. Please choose a card in your deck.");
                continue;
            }
            validCard = true;
        }
        return new Card(toInt(temp[0]), temp[2]);
    }

    public static boolean suitInDeck(Card[] currentDeck, Card card) {
        for (int i = 0; i < currentDeck.length; i++) {
            if (currentDeck[i].getSuit().equalsIgnoreCase(card.getSuit()))
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
        else
            return null;
        return s;
    }

    public static int toInt(String firstExpression){
        if(firstExpression.equalsIgnoreCase("ace"))
            return 1;
        else if(firstExpression.equalsIgnoreCase("jester"))
            return 11;
        else if(firstExpression.equalsIgnoreCase("queen"))
            return 12;
        else if(firstExpression.equalsIgnoreCase("king"))
            return 13;
        else return Integer.parseInt(firstExpression);
    }
}

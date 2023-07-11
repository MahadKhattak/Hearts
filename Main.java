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
        checkStart(players);
    }

    public static void checkStart(Player[] players) {
        for (int i = 0; i <= 3; i++) {
            Card[] currentDeck = players[i].getDeck();
            for (int j = 0; j <= 12; j++)
                if (currentDeck[j].toString().equals("2 of " + "\u2663")) {
                    System.out.println("Player " + (i + 1) + " starts.");
                    System.out.println("Here is your deck: ");
                    for (int k = 0; k < currentDeck.length; k++) {
                        System.out.print("|" + currentDeck[k] + "|" + " ");
                        if (k == 6)
                            System.out.println();
                    }
                }
        }
    }
}

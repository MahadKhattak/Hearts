public class Main {
    public static void main(String[] args) {
        //First let's create a Deck array that stores all the cards needed.
        System.out.println("4 players are needed to play the game.");
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        Card[] sortedDeck = Card.createDeck();
        Card[] randomized = Card.shuffle(sortedDeck);
        Player[] players = new Player[4];
        players[0] = p1;
        players[1] = p2;
        players[2] = p3;
        players[3] = p4;
        Card[] p1deck = players[0].getDeck();
        Card[] p2deck = players[1].getDeck();
        Card[] p3deck = players[2].getDeck();
        Card[] p4deck = players[3].getDeck();
        for (int i = 0; i <= 12; i++) {
            p1deck[i] = randomized[i];
            p2deck[i] = randomized[i + 13];
            p3deck[i] = randomized[i + 26];
            p4deck[i] = randomized[i + 29];
        }
        System.out.println("PLAYER 1 DECK");
        for (int j = 0; j<= 12; j++)
            System.out.println(p1deck[j]);
        System.out.println("PLAYER 2 DECK");
        for (int k = 0; k<= 12; k++)
            System.out.println(p2deck[k]);
        System.out.println("PLAYER 3 DECK");
        for (int l = 0; l<= 12; l++)
            System.out.println(p3deck[l]);
        System.out.println("PLAYER 4 DECK");
        for (int a = 0; a<= 12; a++)
            System.out.println(p4deck[a]);
        checkStart(players);
    }
    //CODE IS BUGGY; Players get duplicates of some cards; will fix soon.
    public static void checkStart(Player[] players){
        for(int i = 0; i<=3; i++) {
            Card[] currentDeck = players[i].getDeck();
            for (int j = 0; j <= 12; j++)
                if(currentDeck[j].toString().equals("2 of Clubs"))
                    System.out.println("Player " + (i+1) + " starts.");
        }
    }
}

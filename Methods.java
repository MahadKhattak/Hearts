import java.util.Scanner;
public class Methods {
    public static void printTable(){
        System.out.println("|--------------------------|");
        System.out.println("|What would you like to do?|");
        System.out.println("|--------------------------|");
        System.out.println("|       N - NEW GAME       |");
        System.out.println("|       S - SAVE GAME      |");
        System.out.println("|       L - LOAD GAME      |");
        Scanner keyIn = new Scanner(System.in);
        String input = keyIn.next();
        if(input.equalsIgnoreCase("n")){
            System.out.println("Passing out cards...");
            //displayCards() method for each player
        }
        else if(input.equalsIgnoreCase("s")){
            //Put code to actually save the game here.
            System.out.println("Uhh, the saving feature actually seems a bit pointless, and the creator doesn't wanna add it. Sorry!");
        }
        else if(input.equalsIgnoreCase("l")){
            System.out.println("Uhh, the loading feature actually seems a bit pointless, and the creator doesn't wanna add it. Sorry!");
            //More code here to read the Player objects from the file and create them, then load the game at the specified turn.
        }
        else{
            System.out.println("That is not a valid option. Please choose again.");
            printTable();
        }
    }
    public static void printDeck(Card[] deck){
        System.out.println("Here is your deck:");
        for (int k = 0; k < deck.length; k++) {
            System.out.print("|" + deck[k] + "|" + " ");
            if (k == deck.length/2)
                System.out.println();
        }
    }
}


import java.util.*;
/**
 * Write a description of class GameIO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameIO
{
    private static WAM wam;
    private static Scanner reader = new Scanner(System.in);
    public static void main(String[] args)
    {
        //Creating Admiral
        System.out.println("Enter an Admiral Name:");
        String name = reader.nextLine();
        wam = new Admiral(name);
        
        int choice = getOption();        
        while (choice != 0)
        {            
            // process choice
            if      (choice == 1){getReserves();}
            else if (choice == 2){getFleet();}
            else if (choice == 3){getShip();}
            else if (choice == 4){commissionShip();}
            else if (choice == 5){fightEncounter();}            
            else if (choice == 6){recommissionShip();}
            else if (choice == 7){decommissionShip();}
            else if (choice == 8){getState();}
            // uncomments after implementing Task 7
            else if (choice == 9){saveGame();}
            else if (choice == 10){loadGame();}
            // output menu & get choice
            choice = getOption();
        }
        System.out.println("\nThank-you");
    }
    
    private static int getOption()
    {
        System.out.println("What would you like to do ?");
        System.out.println("0. Quit");
        System.out.println("1. List ships in reserve");
        System.out.println("2. List ships in the Admiral’s fleet");
        System.out.println("3. View a ship");
        System.out.println("4. Commission a ship into the Admiral’s fleet");
        System.out.println("5. Fight an encounter");
        System.out.println("6. Recommission a ship");
        System.out.println("7. Decommission a ship");
        System.out.println("8. View the game’s state");
        System.out.println("9. Save this game");
        System.out.println("10. Load a game");
        
        System.out.println("Enter your choice");
        // read choice
        int option = reader.nextInt();
        reader.nextLine();
        return option;
    }
    
    private static void getReserves()
    {
        System.out.println(wam.getReserves());
    }
    
    private static void getFleet()
    {
        System.out.println(wam.getFleet());
    }
    
    private static void getShip()
    {
        System.out.println("Enter a ship name:");
        String name = reader.nextLine();
        System.out.println(wam.getShip(name));
    }
    
    private static void commissionShip()
    {
        System.out.println("Enter a ship name:");
        String name = reader.nextLine();
        System.out.println(wam.commissionShip(name));
    }
    
    private static void fightEncounter()
    {
        System.out.println("Enter an Encounter number:");
        int number = reader.nextInt();
        System.out.println(wam.fightEncounter(number));
    }
    
    private static void recommissionShip()
    {
        System.out.println("Enter a ship name:");
        String name = reader.nextLine();
        wam.recommissionShip(name);
    }
    
    private static void decommissionShip()
    {
        System.out.println("Enter a ship name:");
        String name = reader.nextLine();
        wam.decommissionShip(name);
    }
    
    private static void getState()
    {
        System.out.println(wam.toString());
    }
    
    private static void saveGame()
        {
        System.out.println("Enter a save file name:");
        String name = reader.nextLine();
        wam.saveGame(name);
    }
    
    private static void loadGame()
        {
        System.out.println("Enter a load file name");
        String name = reader.nextLine();
        wam.loadGame(name);
    }
}

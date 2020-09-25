
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{
    private static WAM fred = new Admiral("fred");
    
    public static void main(String[] args)
    {
        System.out.println("Get all Encounters");
        System.out.println("**********************************");
        System.out.println(fred.getAllEncounters());
        System.out.println("**********************************");
        System.out.println("Get all Ships in reserve");
        System.out.println("**********************************");
        System.out.println(fred.getReserves());
        System.out.println("**********************************");
        System.out.println("Printing Admiral details");
        System.out.println("**********************************");
        System.out.println(fred.toString());
                
        //Testing view Ship Methods
        System.out.println("\n**********View Ship methods**********");
        System.out.println(fred.findShipInReserve("victory"));
        System.out.println(fred.getShip("sophie"));
        System.out.println(fred.findShipInReserve("fake"));//Doesn't Exist
        System.out.println(fred.getShip("fake")); //Doesn't Exist
        
        //Testing view Encounter Methods
        System.out.println("\n**********Encounter methods**********");
        System.out.println(fred.getEncounter(1));
        System.out.println(fred.getEncounter(9));
        System.out.println(fred.getEncounter(10)); //Doesn't exist
        System.out.println(fred.getEncounter(12)); //Doesn't exist
        
        //Testing Commission Ship methods
        System.out.println("\n**********Commissioning a ship**********");
        System.out.println(fred.commissionShip("victory"));
        System.out.println(fred.commissionShip("beast"));
        System.out.println(fred.commissionShip("fake")); // doesn't exist
        System.out.println(fred.commissionShip("victory")); // not in reserve anymore
        System.out.println(fred.commissionShip("paris")); // not enough money
        System.out.println("\n**********Admiral Details after commissioning of ships**********");
        System.out.println(fred.toString()); //fake and paris should not be in the fleet
        
        //Testing de-Commission Ship methods
        System.out.println("\n**********De-Commissioning Ship Victory**********");
        fred.decommissionShip("victory");
        System.out.println("\n**********Admiral Details after decommissioning of ship Victory**********");
        System.out.println(fred.toString()); //Half of Victory's commission fee(500/2) should be added to the warchest
                                            //Victory should no longer be in the Admirals Fleet
        System.out.println("\n**********Finding victory in Reserves**********");
        System.out.println(fred.findShipInReserve("victory")); //Victory should now be in reserve
        
        //Testing Fight Encounter and robustness
        System.out.println("\n**********Fight Encounter and robustness Test**********");
        System.out.println("\n**********Commisioning more ships for testing**********"); 
        System.out.println(fred.commissionShip("sophie"));
        System.out.println(fred.commissionShip("arrow"));
        System.out.println("\n**********New admiral details**********"); 
        System.out.println(fred.toString()); //Should now have sophie and arrow in fleet
        System.out.println("\n**********Fighting First Encounter**********"); 
        System.out.println(fred.fightEncounter(1));
        System.out.println("\n**********After First fight with encounter 1**********"); 
        System.out.println(fred.getFleet()); //Beast should win and state changed to resting & warChest+ 300plunder
        System.out.println("\n**********After Second fight with encounter 4**********"); 
        System.out.println(fred.fightEncounter(4)); //sophie  should lose and state changed to sunk
        System.out.println(fred.getFleet()); // -200plunder from warchest
        System.out.println("\n**********After Third fight with encounter 6**********"); 
        System.out.println(fred.fightEncounter(6)); //arrow sunk
        System.out.println(fred.getFleet());  //-45plunder from warchest
        System.out.println("\n**********Initiating a fight with no more ships available to fight**********"); 
        System.out.println(fred.fightEncounter(2)); //lost as no ship available
        System.out.println(fred.getFleet()); 
        
        //Testing Re-Commission Ship methods
        System.out.println("\n**********Re-Commissioning RESTING Ship**********");
        fred.recommissionShip("beast"); //successful
        fred.recommissionShip("sophie"); //sophie's state is sunk so shouldn't work
        fred.recommissionShip("arrow"); //arrow's state is sunk so shouldn't work
        System.out.println(fred.getFleet());
                
        //Other robustness tests
        System.out.println("\n**********Other Robustness Tests*********");
        System.out.println("\n**********Find Ship ViCtOrY (lower & uppercase) in reserve**********");
        System.out.println(fred.findShipInReserve("ViCtOrY")); //should convert to lowercase and work
        System.out.println("\n**********Find Ship BEAST (All uppercase) in fleet**********");
        System.out.println(fred.isInAdmiralsFleet("BEAST")); //should convert to lowercase and return true;
    }
}

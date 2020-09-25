import java.util.*;
import java.io.*;
/**
 * Write a description of class Admiral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Admiral implements WAM 
{
    private String admiral;
    private int warChest;

    private HashMap<String, Ship>reserve = new HashMap<String, Ship>();
    private ArrayList<Ship>fleet = new ArrayList<Ship>(); 
    private HashMap<Integer, Encounter>allEncounters = new HashMap<Integer, Encounter>();

    //private BufferedReader reader;

    public Admiral(String name)
    {
        admiral = name.toLowerCase();
        warChest = 1000;
        setupShips();
        setupEncounters();
    }
    //Task 6 Constructor
    public Admiral(String name, String fileName)
    {
        admiral = name;
        warChest = 1000;       
        readShips(fileName);     
        readEncounters();
    }

    //task 1
    private void setupShips()
    {
        reserve.put("victory", new ManOWar ("Victory","Alan Aikin",3,3,30));
        reserve.put("sophie", new Frigate ("Sophie","Ben Baggins",8,16,true));
        reserve.put("endeavour", new ManOWar ("Endevour","Col Cannon",4,2,20));
        reserve.put("arrow", new Sloop ("Arrow","Dan Dare",150,true));
        reserve.put("belerophon", new ManOWar ("Belerophon","Ed Evans",8,3,50));
        reserve.put("surprise", new Frigate ("Surprise","Fred Fox",6,10,false));
        reserve.put("jupiter", new Frigate ("Jupiter","Gil Gamage",7,20,false));
        reserve.put("paris", new Sloop ("Paris","Hal Henry",200,true));
        reserve.put("beast", new Sloop ("Beast","Ian Idle",400,false));
        //Demonstration
        reserve.put("daisy", new ManOWar ("daisy","Dick Deadeye",5,3,30));
        reserve.put("gold", new Corvette("gold","Tom Thumb",5,200,"plymouth",false));
    }

    public String getSunkShips()
    {
        String s = "";
        for(Ship temp : fleet)
        {
            if(temp.getState() == ShipState.SUNK)
            {
                return s = s + temp.toString();
            }
        }
        return null;
    }

    private void setupEncounters()
    {
        allEncounters.put(1, new Encounter (EncounterType.BATTLE,3,300));
        allEncounters.put(2, new Encounter (EncounterType.SKIRMISH,3,120));
        allEncounters.put(3, new Encounter (EncounterType.BLOCKADE,3,150));
        allEncounters.put(4, new Encounter (EncounterType.BATTLE,9,200));
        allEncounters.put(5, new Encounter (EncounterType.BLOCKADE,6,130));
        allEncounters.put(6, new Encounter (EncounterType.SKIRMISH,8,45));
        allEncounters.put(7, new Encounter (EncounterType.BLOCKADE,6,130));
        allEncounters.put(8, new Encounter (EncounterType.BATTLE,4,100));
        allEncounters.put(9, new Encounter (EncounterType.SKIRMISH,5,200));
        //Demonstration
        allEncounters.put(10, new Encounter (EncounterType.BLOCKADE,7,150));
    }

    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the warChest,
     * whether defeated or not, and the ships currently in the 
     * fleet,(or, "No ships" if fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the warChest,
     * whether defeated or not, and the ships currently in the 
     * fleet,(or, "No ships" if fleet is empty)
     **/
    public String toString()
    {
        String s = 
            "Admiral: " + admiral
            + "\nWar Chest: " + warChest
            + "\nIs Defeated?: " + isDefeated()
            + "\nShips in Fleet: ";
        if (fleet.isEmpty())
        {
            return s + "No Ships";
        }
        else
        {
            return s + getFleet();
        }
    }

    /** returns true if War Chest <=0 and the admiral's fleet has no 
     * ships which can be decommissioned. 
     * @returns true if War Chest <=0 and the admiral's fleet has no 
     * ships which can be decommissioned. 
     */
    public boolean isDefeated()
    {
        if(getMoney()<=0)
        {
            for(Ship temp : fleet)
            {
                if(temp.getState() == ShipState.SUNK)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getMoney()
    {
        return warChest;
    }

    /**Returns a String representation of all ships in the reserves
     * @return a String representation of all ships in the reserves
     **/
    public String getReserves()
    {
        String string = "";
        for (Ship temp : reserve.values())
        {
            if(temp.getState() == ShipState.RESERVE)
            {
                string = string + temp.toString() + "\n";
            }
        }
        return string;
    }

    /** Returns details of a reserve ship with the given name
     * @return details of a reserve ship with the given name
     **/
    public String findShipInReserve(String nme)
    {
        if (reserve.containsKey(nme.toLowerCase()))
        {
            Ship temp = reserve.get(nme.toLowerCase());
            return temp.toString();
        }
        return "Ship Doesn't Exist";
    }

    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShip(String nme)
    {
        String string1 = findShipInReserve(nme.toLowerCase());
        String string2 = findShipInFleet(nme.toLowerCase());
        return string1 + string2;
    }

    private String findShipInFleet(String nme)
    {
        for(Ship temp:fleet)
        {
            if(temp.getName().equals(nme.toLowerCase()))
            {
                return temp.toString();
            }
        }
        return "";
    }

    // ***************** Fleet Ships ************************   
    /** Allows a ship to be comissioned to the admiral's fleet, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" 
     * if ship not found, "Not available" if ship is not in the reserve, 
     * "Not enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        Ship ship = reserve.get(nme.toLowerCase());

        if (!isInReserve(nme.toLowerCase()))
        {
            return nme.toLowerCase() + "- Not found"; 
        } 

        if (ship.getState()!= ShipState.RESERVE)
        {
            return nme.toLowerCase() + "- Not Available"; 
        }

        if (getMoney() < ship.getCommissionFee())
        {
            return nme.toLowerCase() + "- Not enough money";
        }
        ship.setState(ShipState.ACTIVE);
        fleet.add(ship);
        warChest = warChest - ship.getCommissionFee();
        return nme + " - Ship Commissioned";
    }

    private boolean isInReserve(String name)
    {
        return reserve.containsKey(name.toLowerCase()); 
    }

    /** Returns true if the ship with the name is in 
     * the admiral's fleet, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name
     * is in the admiral's fleet, false otherwise.
     **/
    public boolean isInAdmiralsFleet(String nme)
    {
        for(Ship temp : fleet)
        {
            if(temp.getName().equals(nme.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }

    /** Removes a ship from the fleet to the reserves, if they are in the fleet
     * pre-condition: isInAdmiralsFleet(nme)
     * @param nme is the name of the ship
     **/
    public void decommissionShip(String nme)
    {
        if(isInAdmiralsFleet(nme.toLowerCase()) == true)
        {
            for(Ship temp:fleet)
            {
                if(temp.getState()== ShipState.ACTIVE ||temp.getState()== ShipState.RESTING )
                {
                    temp.setState(ShipState.RESERVE);
                    reserve.put(temp.getName(),temp);                 
                    warChest = warChest + (temp.getCommissionFee()/2);
                    fleet.remove(temp);
                }  
            }           
        }
    }

    /**Returns a String representation of the ships in the admiral's fleet
     * or the message "No ships hired"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getFleet()
    {
        String temp = "\n"; 
        for(int index = 0; index<fleet.size();index++)
        {
            Ship ship = fleet.get(index); 
            temp = temp + ship.toString(); 
        }
        return temp; 
    }

    /**Restores a ship to the fleet by setting their state to AVAILABLE 
     * @param the name of the ship to be restored
     */
    public void recommissionShip(String nme)
    {
        for(Ship temp : fleet )
        {
            if (temp.getName().equals(nme.toLowerCase())&& temp.getState()== ShipState.RESTING)
            {
                temp.setState(ShipState.ACTIVE); 
            }
        }
    }

    //**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the number of the encounter
     * @returns true if the number represents a encounter
     **/
    public boolean isEncounter(int num)
    {
        if(allEncounters.containsKey(num))
        {
            return true;
        }
        return false;
    }

    /** Retrieves the encounter represented by the encounter 
     * number.Finds a ship from the fleet which can fight the 
     * encounter. The results of fighting an encounter will be 
     * one of the following: “Encounter won by...(ship reference and name)“ 
     * – add plunder to War Chest and ship's state is set to RESTING,  “Encounter 
     * lost as no ship available” – deduct plunder from the War Chest,“Encounter 
     * lost on battle skill level and (ship name) sunk/lost" - deduct plunder from 
     * War Chest and ship state set to sunk. If an encounter is lost and admiral 
     * is completely defeated, add “You have been defeated ” to the output.
     * Ensure that the state of the war chest is also included in the return message.
     * @param encNo is the number of the encounter
     * @return a String showing the result of fighting the encounter
     */ 
    public String fightEncounter(int encNo)
    {
        Encounter encount = allEncounters.get(encNo);
        if(!fleet.isEmpty())
        {
            for(Ship temp : fleet)
            {
                if(temp.canFight(encount) == true && isInAdmiralsFleet(temp.getName()) == true)
                {
                    if(temp.getBattleSkill()< encount.getBattleSkillRequirements())
                    {
                        warChest = warChest - encount.getPlunder();
                        temp.setState(ShipState.SUNK);
                        return "Encounter lost on battle skill level : " + temp.getName() + " sunk/lost" + " WarChest: "+ getMoney();
                    }
                    else
                    {
                        warChest = warChest + encount.getPlunder();
                        temp.setState(ShipState.RESTING);
                        return "Encounter WON by " + temp.getName()+ " WarChest: "+ getMoney();
                    }
                }
            }
        }
        warChest = warChest - encount.getPlunder();
        return "Encounter LOST as no ship available" + " WarChest: " + getMoney();
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        if (allEncounters.containsKey(num))
        {
            Encounter temp = allEncounters.get(num);
            return temp.toString();
        }
        return "Encounter Doesn't Exist";
    }

    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        String result = "";
        Collection<Encounter> myEncounters = allEncounters.values();
        for (Encounter temp : myEncounters)
        {
            result = result + temp.toString();
        }
        return (result);
    }

    //Task 6
    private void readShips(String fileName)
    {
        try 
        {
            BufferedReader ships = new BufferedReader (new FileReader("reserves.txt"));
            String line;
            while ((line = ships.readLine()) != null)
            {
                String[] element = line.split(","); 
                //String name = element[1].toLowerCase();
                //String capt = element[2].toLowerCase();
                if (element[0].equals("ManOWar"))
                {
                    reserve.put(element[1].toLowerCase(), new ManOWar(element[1].toLowerCase(), 
                            element[2].toLowerCase(), Integer.parseInt(element[3]),Integer.parseInt(element[4]),Integer.parseInt(element[5]))); 
                } 
                else if (element[0].equals("Frigate"))
                {
                    reserve.put(element[1].toLowerCase(), new Frigate(element[1].toLowerCase(), 
                            element[2].toLowerCase(), Integer.parseInt(element[3]),Integer.parseInt(element[4]),Boolean.parseBoolean(element[5])));  
                } else if (element[0].equals("Sloop"))
                {
                    reserve.put(element[1].toLowerCase(), new Sloop(element[1].toLowerCase(), 
                            element[2].toLowerCase(),Integer.parseInt(element[4]),Boolean.parseBoolean(element[5])));
                }
                else if (element[0].equals("Corvette"))
                {
                    reserve.put(element[1].toLowerCase(), new Corvette(element[1].toLowerCase(), 
                            element[2].toLowerCase(),Integer.parseInt(element[4]),Integer.parseInt(element[5]),element[6].toLowerCase(),Boolean.parseBoolean(element[5])));
                }
                
            }
        } 
        catch (IOException e) 
        {
            System.out.println (e);
        }
        //test purposes
        //         Collection<Ship> myReserve = reserve.values();
        //         for (Ship xxx : myReserve)
        //         {
        //             System.out.println(xxx.toString());  
        //         }
    }

    private void readEncounters()
    {
        try
        {
            FileInputStream file = new FileInputStream("encounters.txt");
            ObjectInputStream ois  = new ObjectInputStream (file);

            Encounter encount = (Encounter) ois.readObject();
            while (encount != null)
            {
                allEncounters.put(encount.getEncounterNumber(),encount);
                encount = (Encounter)ois.readObject();
            }  
        }
        catch (FileNotFoundException e)  {System.out.println (e);}
        catch (ClassNotFoundException e) {System.out.println (e);}
        catch (IOException e) {System.out.println (e);}
        //test purposes
        //         Collection<Encounter> myEncounter = allEncounters.values();
        //         for (Encounter xxx : myEncounter)
        //         {
        //             System.out.println(xxx.toString());  
        //         }
    }

    // These methods are not needed until Task 7
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fname);
            //OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream oos = new ObjectOutputStream (fileOut);
            oos.writeObject (this);
        }  
        catch (IOException e) {System.out.println (e);}
    }

    /** reads all information about the game from the specified file 
     * and returns an Admiral object
     * @param fname name of file storing the game
     * @return the game (as a Admiral object)
     */
    public Admiral loadGame(String fname)
    {
        try
        {
            FileInputStream file = new FileInputStream("fileName");
            ObjectInputStream ois  = new ObjectInputStream (file);
            Admiral ad = (Admiral)ois.readObject(); 
            admiral = ad.admiral; 
            warChest = ad.warChest; 

            reserve = ad.reserve;
            fleet = ad.fleet;  
            allEncounters= ad.allEncounters; 

            return ad; 
        } 
        catch (Exception e)
        {
            System.out.println(e); 
            return null; 
        }
    }

}

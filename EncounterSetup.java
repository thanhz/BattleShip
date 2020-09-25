import java.io.*;
/**
 * Write a description of class EncounterSetup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EncounterSetup
{
    public static void main (String[] args)
    {
        BufferedReader myIn = new BufferedReader(new InputStreamReader(System.in)); 
        String fileName = "encounters.txt";       
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream (fileOut);
               
            System.out.println ("Enter quit to finish");
            System.out.println ("Press any key to continue: ");
            String string = myIn.readLine();
            while (!string.toLowerCase().equals ("quit"))
            {
               System.out.println ("Enter a Encounter Type (BLOCKADE,BATTLE or SKIRMISH): ");
               String num = myIn.readLine();
               EncounterType type = EncounterType.valueOf(num.toUpperCase());
 
               System.out.println ("Enter a Skill level: ");
               String num1 = myIn.readLine();
               int level = Integer.parseInt (num1);
               
               System.out.println ("Enter plunder: ");
               String num2 = myIn.readLine();
               int plunder = Integer.parseInt (num2);

               Encounter encount = new Encounter(type,level,plunder);
               oos.writeObject (encount);
               System.out.println ("Press any key to continue: ");
               string = myIn.readLine();
            }
            oos.writeObject (null); // write an end of data merker
            oos.close();
        }
        catch (IOException e) {System.out.println (e);}
    }
}

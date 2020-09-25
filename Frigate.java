
/**
 * Write a description of class Frigate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frigate extends Ship
{
    private boolean pinnance;
    private int cannons;
    /**
     * Constructor for objects of class Frigate
     */
    public Frigate(String nme,String cpt,int skill,int cannons,boolean pinnance)
    {
        super(nme,cpt); 
        setBattleSkill(skill);
        this.pinnance = pinnance;
        this.cannons = cannons;
        int count = 0;
        while ( count < cannons )
        {
            count++ ;
            setCommissionFee(count*10);
        }
    }
    
    public boolean canFight(Encounter enc)
    {
        if(enc != null)
        {
            if ((enc.getType()== EncounterType.BLOCKADE && hasPinnance() ==true) || enc.getType()== EncounterType.BATTLE || enc.getType()== EncounterType.SKIRMISH
            && getState() == ShipState.ACTIVE)
            {
                return true; 
            }
        }
        return false; 
    }
    
    public boolean hasPinnance()
    {
        return pinnance;
    }
    
    public int getCannons()
    {
        return cannons;
    }
    
    public String toString()
    {
        return  super.toString() 
        + "\nPinnance : " + pinnance
        + "\nCannons :" + cannons
        + "\n";
    } 
}

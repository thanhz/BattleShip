
/**
 * Write a description of class Sloop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sloop extends Ship 
{
    private boolean doctor;
    /**
     * Constructor for objects of class Sloop
     */
    public Sloop(String nme,String cpt,int fee,boolean doctor )
    {
        super(nme,cpt); 
        setBattleSkill(5);
        this.doctor = doctor;
        int count = 0;
        setCommissionFee(fee);
    }
    
    public boolean canFight(Encounter enc)
    {
        if(enc != null)
        {
            if ((enc.getType()== EncounterType.BATTLE || enc.getType()== EncounterType.SKIRMISH)
            && getState() == ShipState.ACTIVE)
            {
                return true; 
            }
        }
        return false; 
    }
    
    public boolean hasDoctor()
    {
        return doctor;
    }
       
    public String toString()
    {
        return  super.toString() 
        + "\nDoctor : " + doctor
        + "\n";
    } 
}

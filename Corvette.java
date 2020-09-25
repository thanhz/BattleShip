
/**
 * Write a description of class Corvette here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Corvette extends Ship
{
   private boolean doctor;
   private String home;
    
    /**
     * Constructor for objects of class ManOWar
     */
    public Corvette(String nme,String cpt,int skill,int fee,String home,boolean doctor)
    {
        super(nme,cpt);        
        setBattleSkill(skill);
        setCommissionFee(fee);
        this.home = home;
        this.doctor = doctor;
    }
    
    public boolean canFight(Encounter enc)
    {
        if(enc != null)
        {
            if ((enc.getType()== EncounterType.BATTLE && doctor == true)
            && getState() == ShipState.ACTIVE)
            {
                return true; 
            }
        }
        return false; 
    }
    
}

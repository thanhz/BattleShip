
/**
 * Write a description of class ManOWar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ManOWar extends Ship
{
    private int levels;
    private int marines;
    
    /**
     * Constructor for objects of class ManOWar
     */
    public ManOWar(String nme,String cpt,int skill,int levels,int marines)
    {
        super(nme,cpt);
        this.levels = levels;
        this.marines = marines;       
        setBattleSkill(skill);
        if(levels == 2)
        {
            setCommissionFee(300);
        }
        else
        {
            setCommissionFee(500);
        }
    }
    
    public boolean canFight(Encounter enc)
    {
        if(enc != null)
        {
            if ((enc.getType()== EncounterType.BLOCKADE || enc.getType()== EncounterType.BATTLE)
            && getState() == ShipState.ACTIVE)
            {
                return true; 
            }
        }
        return false; 
    }
    
    public int getLevels()
    {
        return levels;
    }
    
    public int getMarines()
    {
        return levels;
    }
    
    public String toString()
    {
        return  super.toString() 
        + "\nLevels : " + levels
        + "\nMarines :" + marines
        + "\n";
    } 
}

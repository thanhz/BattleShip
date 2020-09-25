import java.io.Serializable;
/**
 * Abstract class Ship - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Ship implements Serializable
{
    private String name;
    private String captain;
    private int commissionFee;
    private int battleSkill;
    ShipState state;
    
    public Ship(String nme,String cpt)
    {
        name = nme.toLowerCase();
        captain = cpt;
        state = ShipState.RESERVE;
    }
    
    /**
     * Child classes have their own method
     */
    public abstract boolean canFight(Encounter enc); 
    
    /**
     * Allows the user to set the commission fee
     * @Param fee the new commission fee
     */
    public void setCommissionFee(int fee)
    {
        commissionFee = fee;
    }
    
    /**
     * Allows the user to set the battle skill of the ship
     * @Param skill the new battle skill
     */
    public void setBattleSkill(int skill)
    {
        battleSkill = skill;
    }
    
    /**
     * Allows the user to set the state of the ship
     * @Param state the state of the ship
     */
    public void setState(ShipState state)
    {
        this.state = state;
    }
    
    /**
     * Returns to the name of the ship
     */
    public String getName()
    {
        return name; 
    }
    
    /**
     * Returns to the commission fee
     */
    public int getCommissionFee()
    {
        return commissionFee; 
    }
    
    /**
     * Returns to the battle skill
     */
    public int getBattleSkill()
    {
        return battleSkill; 
    }
    
        /**
     * Returns to the state of the ship
     */
    public ShipState getState()
    {
        return state; 
    }
    
    /**Returns all the details of the ship
     * 
     */
    public String toString()
    {
        return "\nShip Name: " + name
        + "\nCaptain: " + captain 
        + "\nCommission Fee: " + commissionFee
        + "\nBattle Skill: " + battleSkill
        + "\nState: " + state;
    } 
}

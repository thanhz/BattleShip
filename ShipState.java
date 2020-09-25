import java.io.*;
/**
 * Enumeration class UnitState - write a description of the enum class here
 * 
 * @author A.Marczyk
 * @version 12/02/2012
 */
public enum ShipState implements Serializable
{
    RESERVE(" In reserves"), ACTIVE(" Active"), RESTING(" Resting"), SUNK (" Sunk/Lost");
    private String state;
    
    private ShipState(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }
}

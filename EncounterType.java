import java.io.*;
/**
 * Enumeration class EncounterType - write a description of the enum class here
 * 
 * @author A.Marczyk
 * @version 12/02/2012
 */
public enum EncounterType implements Serializable
{
    BLOCKADE(" Blockade"), BATTLE(" Battle"), SKIRMISH (" Skirmish");
    private String type;
    
    private EncounterType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}

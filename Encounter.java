import java.util.*;
import java.io.*;
/**
 * Write a description of class Encounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Encounter implements Serializable
{
    private int encounterNumber;
    private static int number = 1; 
    private EncounterType type;
    private int battleSkillReq;
    private int plunder;
    
    /**
     * Constructor for objects of class Encounter
     */
    public Encounter(EncounterType type,int skillReq,int plunder)
    {
        encounterNumber = number++;
        this.type = type;
        battleSkillReq = skillReq;
        this.plunder = plunder;
    }
    
    public int getEncounterNumber()
    {
        return encounterNumber;
    }
    
    public EncounterType getType()
    {
        return type;
    }
    
    public int getBattleSkillRequirements()
    {
        return battleSkillReq;
    }
    
    public int getPlunder()
    {
        return plunder;
    }
    
    public String toString()
    {
        return "\nEncounter Number:" + encounterNumber 
        + "\nType : " + type
        + "\nBattle Skill Requirement :" + battleSkillReq
        + "\nPlunder :" + plunder
        + "\n";
    } 
}

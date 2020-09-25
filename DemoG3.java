
/**
 * Write a description of class DemoG3 here.
 * 
 * @author (Lam,Tri Thanh + No Partner) 
 * @version (a version number or a date)
 */
public class DemoG3
{
    public void doDemoG3()
    {
        WAM emperor = new Admiral("napoleon");

        System.out.println (emperor.getAllEncounters());
        System.out.println("**********************************");
        
        System.out.println(emperor.getReserves());
        System.out.println("**********************************");
        
        System.out.println(emperor.toString());
        System.out.println("**********************************");
        
        System.out.println(emperor.commissionShip("belerophon"));
        System.out.println(emperor.getMoney());
        System.out.println(emperor.commissionShip("jupiter"));
        System.out.println(emperor.getMoney());
        System.out.println(emperor.commissionShip("victory"));
        System.out.println(emperor.getMoney());
        System.out.println(emperor.commissionShip("leeds"));
        System.out.println(emperor.getMoney());
        System.out.println(emperor.commissionShip("arrow"));
        System.out.println(emperor.getMoney());
        System.out.println(emperor.toString());
        System.out.println("**********************************");
        
        System.out.println(emperor.fightEncounter(1));
        System.out.println(emperor.fightEncounter(5));
        System.out.println(emperor.fightEncounter(4));
        System.out.println(emperor.fightEncounter(9));
        System.out.println(emperor.fightEncounter(1));
        System.out.println(emperor.getFleet());
        System.out.println("**********************************");
        
        emperor.recommissionShip("jupiter");
        System.out.println(emperor.fightEncounter(1));
    }
}

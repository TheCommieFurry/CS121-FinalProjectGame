
/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy
{
    private int damage;
    private int dodge;
    private int health;
    private String name;
    
    public Enemy()
    {
        
    }
    
    public Enemy(int health, int dodge, int damage, String name)
    {
        this.health = health;
        this.dodge = dodge;
        this.damage = damage;
        this.name = name;
    }
    
    //GETERS SETTERS!!!!!!!!!!
    public int gHealth()
    {
        return health;
    }
    
    public int gDodge()
    {
        return dodge;
    }
    
    public int gDamage()
    {
        return damage;
    }
    
    public String gName()
    {
        return name;
    }
    
    public void sHealth(int health)
    {
        this.health = health;
    }
    
    //-------------------------------------------------------------------------
    
    public void printEnemyStats()
    {
        System.out.println(gName());
        System.out.println("Health = " + gHealth());
        System.out.println("Dodge = " + gDodge());
        System.out.println("Damage = " + gDamage());
    }
}

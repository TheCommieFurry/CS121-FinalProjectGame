/**
 * Represents the players character and all of their stats
 *
 * @author Gage R
 * @version 11.13.2024
 */
public class Player
{
    //INSTANCE VARIABLES RAHAHAHA
    private int strength;
    private int agility;
    private int luck;
    private int sanity;
    private int maxSanity;
    private int health;
    private int maxHealth;
    private String name;
    
    //default constructor
    public Player()
    {
        strength = 10;
        agility = 10;
        luck = 10;
        sanity = 100;
        maxSanity = 100;
        health = 100;
        maxHealth = 100;
        name = "Chara";
    }
    
    //GETTERS!!!!!!!!!
    public int gStrength()
    {
        return strength;
    }
    
    public int gAgility()
    {
        return agility;
    }
    
    public int gLuck()
    {
        return luck;
    }
    
    public int gSanity()
    {
        return sanity;
    }
    
    public int gMaxSanity()
    {
        return maxSanity;
    }
    
    public int gHealth()
    {
        return health;
    }
    
    public int gMaxHealth()
    {
        return maxHealth;
    }
    
    //SETTERS!!!!!
    public void sStrength(int strength)
    {
        this.strength = strength;
    }
    
    public void sAgility(int agility)
    {
        this.agility = agility;
    }
    
    public void sLuck(int luck)
    {
        this.luck = luck;
    }
    
    public void sSanity(int sanity)//reduces to maxSanity if goes over
    {
        boolean tooMuch = false;
        do
        {
            if(this.sanity > maxSanity)
            {
                tooMuch = true;
                this.sanity--;
            }
            else
            {
                tooMuch = false;
            }
        }while(tooMuch == true);
    }
    
    public void sMaxSanity(int maxSanity)
    {
        this.maxSanity = maxSanity;
    }
    
    public void sHealth(int health) //reduces to maxHealth if it goes over
    {
        boolean tooMuch = false;
        do
        {
            if(this.health > maxHealth)
            {
                tooMuch = true;
                this.health--;
            }
            else
            {
                tooMuch = false;
            }
        }while(tooMuch == true);
    }
    
    public void sMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }
    
    //------------------------------------------------------------------------
    /**
     * Prints the players stats
     */
    public void printPlayerStats()
    {
        System.out.println(name);
        System.out.println("Strength = " + strength);
        System.out.println("Agility = " + agility);
        System.out.println("Luck = " + luck);
        System.out.println("Sanity = " + sanity + "/" + maxSanity);
        System.out.println("Health = " + health + "/" + maxHealth);
    }
}

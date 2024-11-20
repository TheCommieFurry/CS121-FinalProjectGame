
/**
 * Items of the game
 *
 * @author Gage R
 * @version 11.19.2024
 */
public class Item
{
    int healthMod;
    int sanityMod;
    int statMod;
    int damageMod;
    boolean isWeapon;
    boolean isFood;
    boolean isDrug;
    String name;
    String description;
    
    public Item()
    {
                
    }
    
    public Item(int healthMod, int sanityMod, int statMod, int damageMod, boolean isWeapon, boolean isFood, boolean isDrug, String name, String description)
    {
        this.healthMod = healthMod;
        this.sanityMod = sanityMod;
        this.statMod = statMod;
        this.damageMod = damageMod;
        this.isWeapon = isWeapon;
        this.isFood = isFood;
        this.isDrug = isDrug;
        this.name = name;
        this.description = description;
    }
    
    //GETTERS!!!!!!!!!!!!!!
    
    public int gHealthMod()
    {
        return healthMod;
    }
    
    public int gSanityMod()
    {
        return sanityMod;
    }
    
    public int gStatMod()
    {
        return statMod;
    }
    
    public int gDamageMod()
    {
        return damageMod;
    }
    
    public boolean gIsWeapon()
    {
        return isWeapon;
    }
    
    public boolean gIsFood()
    {
        return isFood;
    }
    
    public boolean gIsDrug()
    {
        return isDrug;
    }
    
    public String gName()
    {
        return name;
    }
    
    public String gDescription()
    {
        return description;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Troops
{
    /**
     * Act - do whatever the Giant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Giant(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 5;
        attackSpeed = 90; //the higher the number the slower the attacks
        animationSpeed = 30;
        
        //health stats
        maxHealth = 40;
        currentHealth = maxHealth;
        
        //attack stats
        damage = 10;
        attackRange = 5;
        
        //miscellaneous stats
        walkImages = null;
        attackImage = null;
        size = walkImages[0].getWidth();
        elixirCost = 5;
        air = false;
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor);
    }
    
    public void act()
    {
        if(spawning){
            spawn();
        }
        if (!crossedBridge){
            moveTowardsTarget(findTarget(Bridge.class));
            if(isTouching(Bridge.class)){
                crossBridge();
            }
        } else {
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            setImage(attackImage);
            ((Towers)a).getHit(damage);
        } else {
            setImage(walkImages[0]);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minion extends Troops
{
    /**
     * Act - do whatever the Minion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Minion(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 10;
        attackSpeed = 60; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        maxHealth = 8;
        currentHealth = maxHealth;
        
        //attack stats
        damage = 3;
        attackRange = 15;
        
        //miscellaneous stats
        images = {};
        size = images[0].getWidth();
        elixirCost = 3;
        air = true;
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor);
    }
    
    public void act()
    {
        actCounter++;
        if(spawning){
            spawn();
        }
        if(findTarget(Troops.class) != null){
            moveTowardsTarget(findTarget(Troops.class));
        } else {
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void attack(Actor a){
        //animation
        if(actCounter % attackSpeed == 0){
            Troops target = (Troops)findTarget(Troops.class);
            if(target != null){
                moveTowardsTarget(target);
            } else {
                ((Towers)a).getHit(damage);
            }
        }
    }
}

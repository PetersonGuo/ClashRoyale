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
        
        //giant stats
        maxSpeed = 5;
        attackSpeed = 90;
        animationSpeed = 30;
        health = 40;
        damage = 10;
        attackRange = 5;
        elixerCost = 5;
        air = false;
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
        //animation
        if(actCounter % attackSpeed == 0){
            ((Towers)a).getHit(damage);
        }
    }
}

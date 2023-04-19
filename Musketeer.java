import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musketeer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Musketeer extends Troops
{
    protected Troops target;
    
    
    public Musketeer(boolean ally){
        super(ally);
        
        //stats
        maxSpeed = 5;
        attackSpeed = 900;
        animationSpeed = 20;
        health = 14;
        damage = 8;
        attackRange = 60;
        elixerCost = 4;
        air = false;
        
    }
    
    public void act()
    {
        super.act();
        
        if (findTarget(Troops.class) != null){
            moveTowardsTarget(findTarget(Troops.class));
        }
        else{
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void attack(Actor a){
        if (actCounter % attackSpeed == 0){
            Troops target = (Troops)findTarget(Troops.class);
            if (target != null){
                moveTowardsTarget(target);
            }   
            else{
                //((Towers)a).getHit(damage);
            }
        }
    }
    
    private void shootpelletAtTarget() {
        getWorld().addObject(new Pellet(target), getX(), getY());
        actCounter = 0;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Towers here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public abstract class Towers extends Actor {
    protected boolean ally; //true if ally, false if enemy
    protected GreenfootImage image; //the image of the tower
    protected int hp, range, shootingCooldown, actsSinceShooting;  //range is the radius of the tower
    protected Troops target; //the troop to target
    
    /**
     * Constructor for objects of class Towers
     * 
     * @param ally true if ally, false if enemy
     */
    public Towers(boolean ally) {
        this.ally = ally;
    }
    
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        actsSinceShooting++;
        if (target != null)
            if (actsSinceShooting >= shootingCooldown)shootArrowAtTarget();
        else
            target = findTarget();
    }
    
    /**
     * Finds the closest target
     * 
     * @return the closest target
     */
    private Troops findTarget() {
        ArrayList<Troops> troopsInRange = (ArrayList<Troops>)getObjectsInRange(range, Troops.class); 
        Troops closest = null;
        if (troopsInRange.size() > 0) {
            closest = troopsInRange.get(0);
            for(Troops t : troopsInRange) {
                if (getDistanceFromTower(t) > getDistanceFromTower(closest))
                    closest = t;
            }
        }
        return closest;
    }
    
    /**
     * Shoots an arrow at the target
     */
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target), getX(), getY());
        actsSinceShooting = 0;
    }
    
    /**
     * Gets the distance from the tower to the troop
     * 
     * @param t the troop to get the distance from
     * @return the distance from the tower to the troop
     */
    public void getHit(int dmg) {
        hp -= dmg;
    }
    
    /**
     * Gets the distance from the tower to the troop
     * 
     * @param t the troop to get the distance from
     * @return the distance from the tower to the troop
     */
    private double getDistanceFromTower(Troops t) {
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    /**
     * Gets whether the tower is an ally
     * 
     * @return true if ally, false if enemy
     */
    public boolean isAlly() {
        return ally;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Towers here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class Towers extends Actor
{
    protected boolean ally;
    protected GreenfootImage image;
    protected int range;
    protected Troops target;
    protected int shootingCooldown;
    protected int actsSinceShooting;
    
    public Towers(boolean ally) {
        this.ally = ally;
    }
    
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actsSinceShooting++;
        if (target != null) {
            if (actsSinceShooting >= shootingCooldown)shootArrowAtTarget();
        }else{
            target = findTarget();
        }
    }
    
    private Troops findTarget() {
        ArrayList<Troops> troopsInRange = (ArrayList<Troops>)getObjectsInRange(range, Troops.class); 
        Troops closest = null;
        if (troopsInRange.size() > 0) {
            closest = troopsInRange.get(0);
            for(Troops t : troopsInRange) {
                if (getDistanceFromTower(t) > getDistanceFromTower(closest)) {
                    closest = t;
                }
            }
        }
        return closest;
    }
    
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target), getX(), getY());
        actsSinceShooting = 0;
    }
    
    private double getDistanceFromTower(Troops t) {
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    public boolean isAlly() {
        return ally;
    }
}

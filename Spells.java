import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Spells here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spells extends Actor {
    protected int speed, damage, elixer, areaOfEffect, targetX, targetY; //areaOfEffect is the radius of the spell
    protected boolean ally; //true if ally, false if enemy
    protected Troops target; //the troop to target
    protected Towers target2; //the tower to target
    
    protected GreenfootImage image, image2; //the image of the spell
    
    /**
     * Constructor for objects of class Spells
     * 
     * @param ally true if ally, false if enemy
     */
    public Spells(boolean ally) { 
        this.ally = ally;
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the spell is added to
     */
    public void addedToWorld (World w) {
        ArrayList<Troops> troopsToHit = (ArrayList<Troops>) w.getObjects(Troops.class);
        ArrayList<Towers> towersToHit = (ArrayList<Towers>) getWorld().getObjects(Towers.class);
        if (troopsToHit.size() > 0) { //if there are troops, turn towards troops first
            targetX = troopTarget().getX();
            targetY = troopTarget().getY();
            turnTowards(targetX, targetY);
        } else { //if no troops, turn and target towers
            targetX = towerTarget().getX();
            targetY = towerTarget().getY();
            turnTowards(targetX, targetY);
        }
    }
    
    /**
     * Get the troop closest to the tower to target
     * @return the troop closest to the tower
     */
    public Troops troopTarget() {
        ArrayList<Troops> troopsToHit = (ArrayList<Troops>) getWorld().getObjects(Troops.class);
        Troops closest = null;
        closest = troopsToHit.get(0);
        // find the troop cloest to the tower to target
        for(Troops t : troopsToHit) {
            if (getDistanceFromTower(t) > getDistanceFromTower(closest)) {
                closest = t;
            }
        }
        return closest;
    }
    
    /**
     * Get the tower with the lowest hp to target
     * @return the tower with the lowest hp
     */
    public Towers towerTarget() {
        ArrayList<Towers> towersToHit = (ArrayList<Towers>) getWorld().getObjects(Towers.class);
        Towers lowest = null;
        lowest = towersToHit.get(0);
        //check for the lowest hp tower to target
        return lowest;
    }
    
    /**
     * Get the distance from the tower to the troop
     * @param t the troop to check the distance from
     * @return the distance from the tower to the troop
     */
    private double getDistanceFromTower(Troops t) {
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    /**
     * Damage method
     */
    public void damage() {
        for(Troops enemy : getObjectsInRange(areaOfEffect, Troops.class)) {
            enemy.getHit(damage * 2);
        }
    }
    
    /**
     * Hit method
     */
    protected void hit() {
        if (intersects(target)) { //hit
            damage();
            getWorld().removeObject(this);
        }
    }
}

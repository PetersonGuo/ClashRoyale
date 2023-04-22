import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Spells here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spells extends Actor {
    protected int speed, damage, elixer, areaOfEffect, targetX, targetY; //areaOfEffect is the radius of the spell
    protected boolean ally; //true if ally, false if enemy
    private King tower;
    private Actor target;
    protected GreenfootImage image, image2; //the image of the spell
    
    /**
     * Constructor for objects of class Spells
     * 
     * @param ally true if ally, false if enemy
     */
    public Spells(boolean ally) { 
        this.ally = ally;
        areaOfEffect = 50;
    }
    
    class Comp implements Comparator<Troops> {
        private King tower;
        public Comp(King t) {
            tower = t;
        }
        
        public int compare(Troops a, Troops b) {
            return (int)Math.sqrt(Math.pow(tower.getX() - b.getX(), 2) + Math.pow(tower.getY() - b.getY(), 2)) - (int)Math.sqrt(Math.pow(b.getX() - tower.getX(), 2) + Math.pow(b.getY() - tower.getY(), 2));
        }
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the spell is added to
     */
    public void addedToWorld (World w) {
        tower = ((MainWorld)getWorld()).getKingTower(ally);
        List<Troops> targets = new ArrayList<>();
        for (Troops t : w.getObjects(Troops.class))
            if (!(t.isAlly() ^ ally))
                targets.add(t);
        Collections.sort(targets, new Comp(tower));
        if (targets.size() > 0) { // if there are troops, turn towards troops first
            targetX = targets.get(0).getX();
            targetY = targets.get(0).getY();
            turnTowards(targetX, targetY);
            target = targets.get(0);
        } else { //if no troops, turn and target towers
            targetX = towerTarget().getX();
            targetY = towerTarget().getY();
            turnTowards(targetX, targetY);
            target = towerTarget();
        }
    }
    
    /**
     * Get the tower with the lowest hp to target
     * @return the tower with the lowest hp
     */
    public Towers towerTarget() {
        List<Towers> towers = new ArrayList<>();
        for (Towers t : getWorld().getObjects(Towers.class))
            if (!(t.isAlly() ^ ally))
                towers.add(t);
        Towers lowest = towers.get(0);
        for (Towers t: towers)  //check for the lowest hp tower to target
            lowest = lowest.getHp() > t.getHp() ? t : lowest;
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
        System.out.println(ally + " " + getObjectsInRange(areaOfEffect, Towers.class));
        for (Troops enemy : getObjectsInRange(areaOfEffect, Troops.class))
            if (enemy.isAlly() ^ ally)
                enemy.getHit(damage * 2);
        for (Towers enemy : getObjectsInRange(areaOfEffect, Towers.class))
            if (!(ally ^ enemy.isAlly()))
                enemy.getHit(damage * 2);
    }
    
    /**
     * Hit method
     */
    protected void hit() {
        if (intersects(target)) { // hit
            damage();
            getWorld().removeObject(this);
        }
    }
}

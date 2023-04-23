import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Spells here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Spells extends Actor {
    protected int speed, damage, elixir, areaOfEffect, targetX, targetY; //areaOfEffect is the radius of the spell
    protected boolean ally; //true if ally, false if enemy
    private King tower;
    private Actor target;
    protected GreenfootImage image, image2; //the image of the spell
    
    /**
     * Constructor for objects of class Spells
     * 
     * @param ally true if ally, false if enemy
     */
    public Spells(boolean ally, Actor target) { 
        this.ally = ally;
        this.target = target;
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the spell is added to
     */
    public void addedToWorld (World w) {
        turnTowards(target.getX(), target.getY());
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
        for (Troops enemy : getObjectsInRange(areaOfEffect, Troops.class))
            if (enemy.isAlly() ^ ally)
                enemy.getHit(damage * 2);
        for (Towers enemy : getObjectsInRange(areaOfEffect, Towers.class))
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * This is a class called "Spells" that represents a spell that can be cast by 
 * a tower or by the player. It has instance variables that store the speed, 
 * damage, elixir cost, area of effect, target coordinates, and whether the 
 * spell was cast by an ally or an enemy. It also has a reference to the tower 
 * that cast the spell and the target that the spell is aimed at. The class has 
 * a constructor that initializes the instance variables and an addedToWorld 
 * method that sets the target coordinates and turns the spell towards the target. 
 * 
 * @author Peterson Guo, Kevin Luo
 * @version 1.0
 */
public abstract class Spells extends Actor {
    protected int speed, damage, elixir, areaOfEffect, targetX, targetY; //speed, damage, elixir cost, area of effect, target's x and y coordinates
    protected boolean ally; //true if ally, false if enemy
    private King tower; //the tower that casted the spell
    private Actor target; //the target of the spell
    protected GreenfootImage image, image2; //the image of the spell
    
    /**
     * Constructor for objects of class Spells
     * 
     * @param ally true if ally, false if enemy
     * @param target the target of the spell
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
        try {
            targetX = target.getX();
            targetY = target.getY();
            turnTowards(targetX, targetY);
        } catch(Exception e) {
            getWorld().removeObject(this);
        }
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
        if (intersects(target) || 
            Math.abs(getX()-targetX) < speed || Math.abs(getY()-targetY) < speed) { // hit
            damage();
            getWorld().removeObject(this);
        }
    }
}

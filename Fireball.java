import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This code implements the Fireball class, which extends the Spells class. Fireball
 * represents a type of spell that shoots arrows at a target. The constructor of 
 * FirebLL takes two parameters, a boolean ally representing whether the spell is 
 * cast by an ally or an enemy, and an Actor target representing the target of 
 * the spell. Fireballs checks whether the spell has hit its target by calling the
 * hit method inherited from Spells. If the spell hits its target, the damage 
 * method of Spells is called to damage all troops and towers within the spell's 
 * areaOfEffect, and the Fireball object is removed from the world.
 * 
 * @author Peterson Guo, Kevin Luo
 * @version 1.0
 */
public class Fireball extends Spells {
    /**
     * Constructor for objects of class Fireball
     * 
     * @param ally Whether the tower is on the left or right side
     * @param target The target of the tower
     */
    public Fireball(boolean ally, Actor target) {
        super(ally, target);
        
        speed = 5;
        damage = 8;
        areaOfEffect = 50;
        image = new GreenfootImage("Fireball0.png");
        image.scale(image.getWidth()/2, image.getHeight()/2);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        hit();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This code implements the Arrows class, which extends the Spells class. Arrows 
 * represents a type of spell that shoots arrows at a target. The constructor of 
 * Arrows takes two parameters, a boolean ally representing whether the spell is 
 * cast by an ally or an enemy, and an Actor target representing the target of 
 * the spell. Arrows checks whether the spell has hit its target by calling the
 * hit method inherited from Spells. If the spell hits its target, the damage 
 * method of Spells is called to damage all troops and towers within the spell's 
 * areaOfEffect, and the Arrows object is removed from the world.
 * 
 * @author Peterson Guo, Kevin Luo
 * @version 1.0
 */
public class Arrows extends Spells {
    /**
     * Constructor for objects of class Arrows
     * 
     * @param ally Whether the tower is on the left or right side
     * @param target The target of the tower
     */
    public Arrows(boolean ally, Actor target) {
        super(ally, target);
        
        speed = 5;
        areaOfEffect = 60;
        damage = 5;
        image = new GreenfootImage("ArrowSpell0.png");
        image.scale(image.getWidth()/3, image.getHeight()/3);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Arrows wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        hit();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrows here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrows extends Spells {
    /**
     * Constructor for objects of class Arrows
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Arrows(boolean ally, Actor target) {
        super(ally, target);
        
        speed = 5;
        areaOfEffect = 50;
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

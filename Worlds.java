import greenfoot.*;
/**
 * Write a description of class Worlds here.
 * 
 * @author Peterson Guo 
 * @version 1.0
 */
public abstract class Worlds extends World {
    /**
     * Constructor for objects of class Worlds
     */
    public Worlds() {
        super(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT, 1);
        
        GreenfootImage bg = new GreenfootImage("cr.jpg");
        bg.scale(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT);
        setBackground(bg);
    }
    
    public abstract void nextWorld();
}

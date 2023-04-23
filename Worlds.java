import greenfoot.*;
/**
 * Wrapper class for all worlds in the game
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
        
        GreenfootImage bg = new GreenfootImage("NewMap2.png");
        bg.scale(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT);
        setBackground(bg);
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public abstract void nextWorld();
}

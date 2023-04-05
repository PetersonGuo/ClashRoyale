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
        super(FINAL.WORLD_SIZE, FINAL.WORLD_SIZE, 1);
        
        GreenfootImage bg = new GreenfootImage(FINAL.WORLD_SIZE, FINAL.WORLD_SIZE);
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, FINAL.WORLD_SIZE, FINAL.WORLD_SIZE);
        setBackground(bg);
    }
    
    public abstract void nextWorld();
}

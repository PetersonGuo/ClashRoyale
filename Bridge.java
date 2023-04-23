import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bridge for troops to cross
 * 
 * @author Kelby To
 * @version 1.0
 */
public class Bridge extends Actor {
    /**
     * Constructor for objects of class Bridge
     */
    public Bridge() {
        GreenfootImage image = new GreenfootImage(48,50);
        image.setColor(new Color(100,0,0,100));
        image.fill();
        setImage(image);
    }
}

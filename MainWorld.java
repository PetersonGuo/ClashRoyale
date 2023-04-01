import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainWorld here.
 * 
 * Sources:
 * Arrow https://www.pngimages.pics/images/quotes/english/general/transparent-background-arrow-head-png-52650-242281.png
 * 
 * @author Peterson Guo 
 * @version 1.0
 */
public class MainWorld extends Worlds {
    /**
     * Constructor for objects of class MainWorld.
     */
    public MainWorld() {
        
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new EndScreen());
    }
}

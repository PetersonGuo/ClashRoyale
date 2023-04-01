import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class EndScreen extends Worlds {
    /**
     * Constructor for objects of class EndScreen.
     */
    public EndScreen() {
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new ChooseScreen());
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class WelcomeScreen extends Worlds {

    /**
     * Constructor for objects of class WelcomeScreen.
     */
    public WelcomeScreen() {
        
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new ChooseScreen());
    }
}

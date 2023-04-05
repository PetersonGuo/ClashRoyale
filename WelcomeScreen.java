import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class WelcomeScreen extends Worlds {
    private static int act;
    private final static int shortPulse = 10, longPulse = 30; // # of acts for morse code pulsing
    /**
     * Constructor for objects of class WelcomeScreen.
     */
    public WelcomeScreen() {
        act = 0;
        
        addObject(new Text("Welcome to PACMAN!", Color.WHITE, 50), FINAL.WORLD_SIZE / 2, FINAL.WORLD_SIZE / 4);
        addObject(new Text("Press '" + FINAL.NEXT_WORLD_BUTTON + "' to continue", Color.WHITE, 30), FINAL.WORLD_SIZE / 2, FINAL.WORLD_SIZE * 3 / 4);
    }
    // .--. .-. . ... ... / .----. ... .--. .- -.-. . .----. / - --- / -.-. --- -. - .. -. ..- .
    
    public void act() {
        if (Greenfoot.isKeyDown(FINAL.NEXT_WORLD_BUTTON))
            nextWorld();
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new ChooseScreen());
    }
}

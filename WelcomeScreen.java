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
        
        addObject(new Text("CLASH ROYALE", Color.WHITE, 50), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 4);
        addObject(new Text("Press '" + FINAL.NEXT_WORLD_BUTTON + "' to continue", Color.WHITE, 30), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT * 3 / 4);
    }
    
    /**
     * Act - do whatever the WelcomeScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.isKeyDown(FINAL.NEXT_WORLD_BUTTON)) // If the user presses the next world button
            nextWorld();
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public void nextWorld() {
        Greenfoot.setWorld(new ChooseScreen());
    }
}

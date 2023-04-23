import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Timer for the game to end
 * 
 * @author Kevin Luo
 * @version 1.0
 */
public class Timer extends Actor {
    private int timer; //about 120 seconds
 
    /**
     * Constructor for objects of class Timer
     */
    public Timer() {
        timer = FINAL.GAME_TIME;
        updateImage();
    }
     
    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        timer--;
        if (timer % 60 == 0)
            updateImage();
        else if (timer <= 0) 
            ((Worlds)getWorld()).nextWorld();
    }
     
    /**
     * Update the image to show the current time.
     */
    private void updateImage() {
        setImage(new GreenfootImage(timer / 3600 + ":" + timer / 60 % 60, 20, Color.BLACK, Color.RED));
    }
}

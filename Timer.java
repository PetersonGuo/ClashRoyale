import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor {
    private int timer; //about 120 seconds
 
    public Timer() {
        timer = FINAL.GAME_TIME;
        updateImage();
    }
     
    public void act() {
        timer--;
        if (timer % 60 == 0)
            updateImage();
        else if (timer <= 0) 
            ((Worlds)getWorld()).nextWorld();
    }
     
    private void updateImage() {
        setImage(new GreenfootImage(timer / 3600 + ":" + timer / 60 % 60, 20, Color.BLACK, Color.RED));
    }
}

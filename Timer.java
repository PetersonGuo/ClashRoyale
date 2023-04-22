import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    private int timer = 7200; //about 120 seconds
 
    public Timer()
    {
        updateImage();
    }
     
    public void act()
    {
        timer--;
        if (timer % 60 == 0){
            updateImage();
        }
    }
     
    private void updateImage()
    {
        setImage(new GreenfootImage("Time\n" + timer/60, 20, Color.BLACK, Color.RED));
    }
    
    public boolean timeIsUp(){
        return timer == 0;
    }
}

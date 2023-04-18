import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class King here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class King extends Towers
{
    public King(boolean ally) {
        super(ally);
        image = (ally) ? new GreenfootImage("king tower 1.png") : new GreenfootImage("king tower 2.png") ;
        range = 80;
        setImage(image);
        shootingCooldown = 30;
    }
    
    /**
     * Act - do whatever the King wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // public void act()
    // {
        // // Add your action code here.
    // }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class King here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class King extends Towers
{
    public King(boolean ally) {
        super(ally);
        hp = 200;
        range = 80;
        shootingCooldown = 30;
        image = (ally) ? new GreenfootImage("king tower 1.png") : new GreenfootImage("king tower 2.png") ;
        image.scale(60, 60);
        setImage(image);
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Normal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Princess extends Towers
{
    public Princess(boolean ally) {
        super(ally);
        hp = 100;
        range = 60;
        shootingCooldown = 45;
        image = (ally) ? new GreenfootImage("princess tower 1.png") : new GreenfootImage("princess tower 2.png") ;
        setImage(image);
    }
    
    /**
     * Act - do whatever the Normal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // public void act()
    // {
        // // Add your action code here.
    // }
}

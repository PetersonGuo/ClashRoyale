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
        image = new GreenfootImage(24,24);
        image.setColor(Color.YELLOW);
        image.fill();
        range = 60;
        setImage(image);
        shootingCooldown = 45;
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

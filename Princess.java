import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Normal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Princess extends Towers {
    /**
     * Constructor for objects of class Princess
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Princess(boolean ally) {
        super(ally);
        hp = 100;
        range = 60;
        shootingCooldown = 45;
        image = (ally) ? new GreenfootImage("princess tower 1.png") : new GreenfootImage("princess tower 2.png") ;
        setImage(image);
    }
}

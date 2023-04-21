import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Spells {
    /**
     * Constructor for objects of class Fireball
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Fireball(boolean ally) {
        super(ally);
        
        speed = 20;
        image = new GreenfootImage("Fireball0.png");
        image2 = new GreenfootImage("Fireball1.png");
    }
    
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        hit();
    }
}

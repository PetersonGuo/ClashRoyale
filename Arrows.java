import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrows here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrows extends Spells {
    /**
     * Constructor for objects of class Arrows
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Arrows(boolean ally) {
        super(ally);
        
        speed = 15;
        image = new GreenfootImage("arrow spell.png");
        image = new GreenfootImage("arrow spell2.png");
    }
    
    /**
     * Act - do whatever the Arrows wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        hit();
    }
}

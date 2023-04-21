import greenfoot.*;

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arrow extends Actor { 
    private int speed, damage; //speed and damage of arrow
    private Troops target; //target of arrow
    private int targetX, targetY; //target's x and y coordinates 
    private GreenfootImage image; //image of arrow
    
    /**
     * Constructor for objects of class Arrow
     * 
     * @param target the target of the arrow
     */
    public Arrow(Troops target) {
        // initialise instance variables
        speed = 5;
        damage = 2;
        this.target = target;
        image = new GreenfootImage("arrow.png");
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the arrow is added to
     */
    public void addedToWorld(World w) {
        targetX = target.getX();
        targetY = target.getY();
        turnTowards(targetX, targetY);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        if (intersects(target)) { //hit
            target.getHit(damage);
            getWorld().removeObject(this);
        } else if (Math.abs(getX()-targetX) < 6 || Math.abs(getY()-targetY) < 6 ) // miss
            getWorld().removeObject(this);
    }
}

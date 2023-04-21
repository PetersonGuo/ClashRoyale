import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author Kevin Luo
 * @version (a version number or a date)
 */
public class Pellet extends Projectile {
    /**
     * Constructor for objects of class Bullet
     * 
     * @param target
     */
    public Pellet(Troops target) {
        super(target);
        
        speed = 5;
        damage = 5;
        this.target = target;
        image = new GreenfootImage("Arrow.png");
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(speed);
        hit();
    }
}

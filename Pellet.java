import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author Kevin Luo
 * @version 1.0
 */
public class Pellet extends Projectile {
    /**
     * Constructor for objects of class Bullet
     * 
     * @param target
     */
    public Pellet(Actor target, double dmgMultiplyer) {
        super(target);
        
        speed = 5;
        damage = 7;
        this.target = target;
        image = new GreenfootImage("Bullet.png");
        image.scale(10, 10);
        setImage(image);
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

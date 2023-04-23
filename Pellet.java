import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pellet class for all bullets in the game
 * 
 * @author Kevin Luo, Isaac Chan
 * @version 1.0
 */
public class Pellet extends Projectile {
    /**
     * Constructor for objects of class Bullet
     * 
     * @param target The target of the tower
     * @param dmgMultiplyer The damage multiplyer of the tower
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
    public void act() {
        move(speed);
        hit();
    }
}

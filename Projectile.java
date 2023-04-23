import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * It has instance variables to store speed, damage, target, target's x and y 
 * coordinates, image, direction and rotation of the projectile. It has a 
 * constructor that takes a target as a parameter. It also has a hit method 
 * that checks if the target intersects with the projectile and deals damage 
 * accordingly. If the target is null or the projectile misses the target, it 
 * is removed from the world.
 * 
 * @author Kevin Luo, Isaac Chan
 * @version 1.0
 */
public class Projectile extends Actor {
    protected int speed; //speed of projectiles
    protected double damage; //damage of the projectile
    protected Actor target; //target of the projectile
    protected int targetX, targetY; //target's x and y coordinates
    protected GreenfootImage image; //image of the projectile
    protected int direction; //direction of the projectile
    protected int rotation; //rotation of the projectile
    
    /**
     * Constructor for objects of class Projectile
     * 
     * @param target the target of the projectile
     */
    public Projectile(Actor target) {
        this.target = target;
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the projectile is added to
     */
    public void addedToWorld(World w) {
        targetX = target.getX();
        targetY = target.getY();
        turnTowards(targetX, targetY);
    }
    
    /**
     * Hit method
     */
    protected void hit() {
        if (target != null && intersects(target)) {
            //hit
            if(target instanceof Troops){ //if the target is a troop
                ((Troops)target).getHit(damage);
            } else if (target instanceof Towers && ((Towers)target).isAlive()){ //if the target is a tower
                ((Towers)target).getHit(damage);
            }
            getWorld().removeObject(this);
        } else if (Math.abs(getX()-targetX) < 6 || Math.abs(getY()-targetY) < 6 ) //miss
            getWorld().removeObject(this);
    }
}

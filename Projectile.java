import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author Kevin Luo 
 * @version 1.0
 */
public class Projectile extends Actor {
    protected int speed, damage; //speed and damage of the projectile
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
        if (intersects(target)) {
            //hit
            if(target instanceof Troops){ //if the target is a troop
                ((Troops)target).getHit(damage);
            } else if (target instanceof Towers){ //if the target is a tower
                ((Towers)target).getHit(damage);
            }
            getWorld().removeObject(this);
        } else if (Math.abs(getX()-targetX) < 6 || Math.abs(getY()-targetY) < 6 ) //miss
            getWorld().removeObject(this);
    }
}

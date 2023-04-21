import greenfoot.*;

/**
 * An arrow that is launched from a Troop or a Tower.
 * The arrow will do damage to the target when it hits it.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */

public class Arrow extends Actor {
    private int speed;
    private int damage;
    private Troops target;
    private int targetX, targetY;
    private GreenfootImage image;
    
    public Arrow(Troops target) {
        speed = 5;
        damage = 3;
        this.target = target;
        image = new GreenfootImage("arrow.png");
    }
    
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
        move(speed); //move in the set direction
        if (intersects(target)) {
            //hit
            target.getHit(damage);
            getWorld().removeObject(this);
        } else if (Math.abs(getX()-targetX) < 5 || Math.abs(getY()-targetY) < 5) {
            //miss
            getWorld().removeObject(this);
        }
    }
}

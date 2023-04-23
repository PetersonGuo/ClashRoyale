import greenfoot.*;

/**
 * An arrow that is launched from a Troop or a Tower.
 * The arrow will do damage to the target when it hits it.
 * 
 * @author Kelby To 
 * @version 1.0
 */
public class Arrow extends Actor { 
    private double damage; //damage of arrow
    private Actor target; //target of arrow
    private int targetX, targetY, speed; //target's x and y coordinates and speed of arrow 
    private GreenfootImage image; //image of arrow
    
    /**
     * Constructor for objects of class Arrow
     * 
     * @param target the target of the arrow
     * @param dmgMultiplyer the damage multiplyer
     */
    public Arrow(Actor target, double dmgMultiplyer) {
        // initialise instance variables
        speed = 5;
        damage = 3 * dmgMultiplyer;
        this.target = target;
        GreenfootImage img = new GreenfootImage("Arrow.png");
        img.scale(img.getWidth()/2, img.getHeight()/2);
        setImage(img);
    }
    
    /**
     * Added to world method
     * 
     * @param w the world the arrow is added to
     */
    public void addedToWorld(World w) {
        try{
            targetX = target.getX();
            targetY = target.getY();
            turnTowards(targetX, targetY);
        }catch(Exception e){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move(speed);
        if (intersects(target)) { //hit
            if(target instanceof Troops)
                ((Troops)target).getHit(damage);
            else if(target instanceof Towers)
                ((Towers)target).getHit(damage);
            getWorld().removeObject(this);
        } else if (Math.abs(getX()-targetX) < 6 || Math.abs(getY()-targetY) < 6 ) // miss
            getWorld().removeObject(this);
    }
}

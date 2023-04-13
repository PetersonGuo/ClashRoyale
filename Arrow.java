import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class Arrow extends Actor
{
    private int speed;
    private int damage;
    private Troops target;
    private int targetX, targetY;
    private GreenfootImage image;
    
    public Arrow(Troops target){
        speed = 5;
        damage = 2;
        this.target = target;
        image = new GreenfootImage("arrow.png");
    }
    
    public void addedToWorld(World w){
        targetX = target.getX();
        targetY = target.getY();
        turnTowards(targetX, targetY);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(speed);
        if(intersects(target)){
            //hit
            target.getHit(damage);
            getWorld().removeObject(this);
        }else if(Math.abs(getX()-targetX) < 6 || Math.abs(getY()-targetY) < 6 ){
            //miss
            getWorld().removeObject(this);
        }
    }
}

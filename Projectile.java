import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author Kevin Luo 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    protected int speed;
    protected int damage;
    protected Troops target;
    protected int targetX, targetY;
    protected GreenfootImage image;
    protected int direction;
    protected int rotation;
    
    public Projectile(Troops target){
        this.target = target;
    }
    
    public void addedToWorld(World w){
        targetX = target.getX();
        targetY = target.getY();
        turnTowards(targetX, targetY);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    protected void hit(){
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

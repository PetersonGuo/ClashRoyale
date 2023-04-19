import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class Arrow extends Projectile
{
    public Arrow(Troops target){
        super(target);
        
        speed = 5;
        damage = 2;
        this.target = target;
        image = new GreenfootImage("arrow.png");
    }
    
    public void act()
    {
        move(speed);
        hit();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author Kevin Luo
 * @version (a version number or a date)
 */
public class Pellet extends Projectile
{
    public Pellet(Troops target){
        super(target);
        
        speed = 5;
        damage = 5;
        this.target = target;
        image = new GreenfootImage("arrow.png");
    }
    
    public void act()
    {
        move(speed);
        hit();
    }
}

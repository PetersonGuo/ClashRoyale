import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Spells
{
    public Fireball(boolean ally){
        super(ally);
        
        speed = 20;
        image = new GreenfootImage("arrow spell.png");
        image = new GreenfootImage("arrow spell2.png");
    }
    
    public void act()
    {
        move(speed);
        hit();
    }
}

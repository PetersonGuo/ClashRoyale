import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrows here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrows extends Spells
{
    public Arrows(boolean ally){
        super(ally);
        
        speed = 15;
        setImage("arrow spell.png");
    }
    
    public void act()
    {
        move(speed);
        hit();
    }
}

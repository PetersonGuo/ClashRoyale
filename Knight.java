import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Troops
{
    
    public Knight(boolean ally){
        super(ally);
        
        maxSpeed = 8;
        attackSpeed = 60;
        animationSpeed = 15;
        
        health = 8;
        damage = 3;
        attackRange = 15;
        detectionRange = 60;

        elixerCost = 3;
        air = true;
        
        walkImages = new GreenfootImage[3];
        walkImages[0] = new GreenfootImage("knight walk 0.png");
        walkImages[1] = new GreenfootImage("knight walk 1.png");
        walkImages[2] = new GreenfootImage("knight walk 2.png");
        attackImage = new GreenfootImage("knight attack.png");
        setImage(walkImages[0]);
    }
    
    public void attack(Actor a){
        
    }
        
    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
    }
}

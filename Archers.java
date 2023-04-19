import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archers extends Troops
{
    protected Troops target; 
    int imageNumber;
    GreenfootImage[] images;
    public Archers(boolean ally){
        super(ally);
        
        //stats
        maxSpeed = 5;
        attackSpeed = 60;
        animationSpeed = 10;
        health = 10;
        damage = 4;
        attackRange = 40;
        elixerCost = 3;
        air = false;
        
        images = new GreenfootImage[imageNumber];
        
        String[] images = {"walking archer 0.png", "walking archer 1.png", "walking archer 2.png", "new archer.png", "archer attack.png"};
    }
    
    public void act()
    {
        super.act();
        
        if (findTarget(Troops.class) != null){
            moveTowardsTarget(findTarget(Troops.class));
        }
        else{
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void attack(Actor a){
        if (actCounter % attackSpeed == 0){
            Troops target = (Troops)findTarget(Troops.class);
            if (target != null){
                moveTowardsTarget(target);
            }   
            else{
                //((Towers)a).getHit(damage);
            }
        }
    }
    
    public GreenfootImage[] animation(){
        GreenfootImage[] images = new GreenfootImage[imageNumber];
        for(int i = 0; i < images.length; i++)
        {
            images[i] = new GreenfootImage("walking archer " + i + ".png");
        }
        return images;
    }
    
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target), getX(), getY());
        actCounter = 0;
    }
}

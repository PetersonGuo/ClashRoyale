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
    
    public Archers(boolean ally){
        super(ally);
        
        //stats
        maxSpeed = 5;
        attackSpeed = 60;
        animationSpeed = 10;
        currentHealth = 10;
        damage = 4;
        attackRange = 40;
        elixirCost = 3;
        air = false;
        
        setImage("new archer.png");
        
        walkImages = new GreenfootImage[3];
        
        walkImages[0] = new GreenfootImage("walking archer 0.png");
        walkImages[1] = new GreenfootImage("walking archer 1.png");
        walkImages[2] = new GreenfootImage("walking archer 2.png");
        attackImages[0] = new GreenfootImage("archer attack.png");
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
            shootArrowAtTarget();
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

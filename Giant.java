import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Troops
{
    /**
     * Act - do whatever the Giant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Giant(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 5;
        attackSpeed = 90; //the higher the number the slower the attacks
        animationSpeed = 30;
        
        //health stats
        maxHealth = 40;
        currentHealth = maxHealth;
        
        //attack stats
        damage = 10;
        attackRange = 5;
        
        //miscellaneous stats
        size = 40;
        
        walkImages = new GreenfootImage[3];
        walkImages[0] = new GreenfootImage("giant_0.png");
        walkImages[1] = new GreenfootImage("giant_walk_2.png");
        walkImages[2] = new GreenfootImage("giant_walk_3.png");
        
        attackImages = new GreenfootImage[3];
        attackImages[0] = new GreenfootImage("giant_attack.png");
        attackImages[1] = new GreenfootImage("giant_attack_1.png");
        attackImages[2] = new GreenfootImage("giant_attack_2.png");
        
        elixirCost = 5;
        air = false;
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor);
    }
    
    public void act()
    {
        if(spawning){
            spawn();
        }
        if (!crossedBridge){
            moveTowardsTarget(findTarget(Bridge.class));
            if(isTouching(Bridge.class)){
                crossBridge();
            }
        } else {
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            animate(attackImages);
            ((Towers)a).getHit(damage);
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
}

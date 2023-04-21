import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Troops {
    /**
     * Constructor for objects of class Giant
     * 
     * @param ally Whether the tower is on the left or right side
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
        walkImages[0] = new GreenfootImage("Giant0.png");
        walkImages[1] = new GreenfootImage("GiantWalk2.png");
        walkImages[2] = new GreenfootImage("GiantWalk3.png");
        
        attackImages = new GreenfootImage[3];
        attackImages[0] = new GreenfootImage("GiantAtk.png");
        attackImages[1] = new GreenfootImage("GiantAtk1.png");
        attackImages[2] = new GreenfootImage("GiantAtk2.png");
        
        elixirCost = 5;
        air = false;
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor);
    }
    
    /**
     * Act - do whatever the Giant wants to do. This method is called whenever
     */
    public void act() {
        if(spawning)
            spawn();
        if (!crossedBridge) {
            moveTowardsTarget(findTarget(Bridge.class));
            if (isTouching(Bridge.class))
                crossBridge();
        } else
            moveTowardsTarget(findTarget(Towers.class));
    }
    
    /**
     * attack - attacks the target
     * 
     * @param a - the target
     */
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            animate(attackImages);
            ((Towers)a).getHit(damage);
        } else //while not attacking
            setImage(walkImages[0]);
    }
}

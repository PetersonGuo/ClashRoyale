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
     * Constructor for objects of class Giant
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Giant(boolean ally) {
        super(ally);
        
        //speed stats
        maxSpeed = 0.5;
        attackSpeed = 500; //the higher the number the slower the attacks
        animationSpeed = 30;
        
        //health stats
        currentHealth = maxHealth = 800;
        
        //attack stats
        damage = 50;
        size = 45;
        attackRange = 10 + size;
        attackSound = new GreenfootSound("GiantAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 5;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("GiantWalk"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[3];
        for(int i = 0; i < attackImages.length; i++) {
            attackImages[i] = new GreenfootImage("GiantAtk"+ i + ".png");
            attackImages[i].scale(size, size);
        }
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Giant wants to do. This method is called whenever
     */
    public void act()
    {
        super.act();
        if (spawning) {
            spawn();
        } else if (alive) {
            if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class)) {
                    crossBridge();
                }
            } else { //If crossed bridge
                moveTowardsTarget(findTarget(Towers.class));
            }
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    public void addedToWorld(World w) {
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * attack - attacks the target
     * 
     * @param a - the target
     */
    public void attack(Actor a) { // Attack the target
        if (actCounter % attackSpeed <= attackSpeed / 5) { // If the attack counter is reached
            animate(attackImages);
            ((Towers)a).getHit(damage);
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
}

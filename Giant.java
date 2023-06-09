import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Giants are a type of Troops, which can be either allies or enemies. The Giant's 
 * behavior involves finding a tower, moving towards it, and attacking it.The Giant
 * has various stats, such as health, attack damage, and attack speed, as well as a 
 * SuperStatBar to display its health. The class includes methods for attacking the 
 * target.
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class Giant extends Troops {
    /**
     * Constructor for objects of class Giant
     * 
     * @param ally Whether the tower is on the left or right side
     * @param hpMultiplyer The health multiplyer of the troop
     * @param dmgMultiplyer The damage multiplyer of the troop
     */
    public Giant(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 100; //the higher the number the slower the attacks
        animationSpeed = 30;
        
        //health stats
        currentHealth = maxHealth = 60 * hpMultiplyer;
        
        //attack stats
        damage = 10 * dmgMultiplyer;
        size = 45;
        attackRange = 10 + size;
        attackSound = new Sound("GiantAttack.mp3");
        
        //miscellaneous stats
        size = 40;
        
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
        
        healthBar = new SuperStatBar((int)maxHealth, (int)currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Giant wants to do. This method is called whenever
     */
    public void act() {
        super.act();
        if (spawning)
            spawn();
        else if (alive) {
            if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class))
                    crossBridge();
            } else //If crossed bridge
                moveTowardsTarget(findTarget(Towers.class));
            die();
        } else
            getWorld().removeObject(this);
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
        animate(attackImages);
        if (actCounter % attackSpeed == 0) // If the attack counter is reached
            ((Towers)a).getHit(damage);
        else if (actCounter % attackSpeed <= 10)
            setImage(walkImages[0]);
    }
}

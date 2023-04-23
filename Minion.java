import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Minions are a type of air Troops, which can be either allies or enemies. 
 * The Minion's behavior involves finding a target, moving towards it, and 
 * attacking it using an ax. If there is no target, the Minion will move 
 * towards the Towers class. The Minion has various stats, such as health, 
 * attack damage, and attack speed, as well as a SuperStatBar to display 
 * its health. The class includes methods for attacking at the target.
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class Minion extends Troops {
    /**
     * Constructor for objects of class Minion
     * @param ally Whether the tower is on the left or right side
     * @param hpMultiplyer the amount to multiply the health by
     * @param dmgMultiplyer the amount to multiply the damage by
     */
    public Minion(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 2;
        attackSpeed = 50; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 25 * hpMultiplyer;
        
        //attack stats
        damage = 3 * dmgMultiplyer;
        size = 25;
        attackRange = 15 + size; 
        attackSound = new GreenfootSound("MinionAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 3;
        air = true;
        
        walkImages = new GreenfootImage[4];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("Minion"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("Minion.png");
        attackImages[0].scale(size, size);
        
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar((int)maxHealth, (int)currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Minion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (spawning) {
            spawn();
        } else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null) { // If there is a target
                moveTowardsTarget(troop);
            } else { // If there is no target
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
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a) {
        animate(attackImages);
        if (actCounter % attackSpeed == 0) {
            if (a instanceof Troops) //If target is a troop
                ((Troops)a).getHit(damage);
            else if (a instanceof Towers) //If target is a tower
                ((Towers)a).getHit(damage);
        } else if (actCounter % attackSpeed <= 10)
            setImage(walkImages[0]);
    }
}

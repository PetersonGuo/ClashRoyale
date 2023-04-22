import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minion extends Troops
{
    /**
     * Act - do whatever the Minion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Minion(boolean ally) {
        super(ally);
        
        //speed stats
        maxSpeed = 2;
        attackSpeed = 50; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 20;
        
        //attack stats
        damage = 1;
        size = 25;
        attackRange = 15 + size; 
        detectionRange = 180;
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
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
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
            if (a instanceof Troops) { //If target is a troop
                ((Troops)a).getHit(damage);
            }else if (a instanceof Towers) { //If target is a tower
                ((Towers)a).getHit(damage);
            } 
        } else if (actCounter % attackSpeed <= 10){
            setImage(walkImages[0]);
        }
    }
}

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
     * Constructor for objects of class Minion
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Minion(boolean ally) {
        super(ally);
        
        //speed stats
        maxSpeed = 10;
        attackSpeed = 60; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        maxHealth = 8;
        currentHealth = maxHealth;
        
        //attack stats
        damage = 3;
        attackRange = 15;
        
        //miscellaneous stats
        walkImages = new GreenfootImage[4];
        walkImages[0] = new GreenfootImage("minion_0.png");
        walkImages[1] = new GreenfootImage("minion_1.png");
        walkImages[2] = new GreenfootImage("minion_2.png");
        walkImages[3] = new GreenfootImage("minion_3.png");
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("minion_attack.png");
        
        size = walkImages[0].getWidth();
        elixirCost = 3;
        air = true;
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor);
    }
    
    /**
     * Act - do whatever the Minion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actCounter++;
        if(spawning){
            spawn();
        }
        if(findTarget(Troops.class) != null){
            moveTowardsTarget(findTarget(Troops.class));
        } else {
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    /**
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            Troops target = (Troops)findTarget(Troops.class);
            if(target != null){ //if enemy moves out of the way
                moveTowardsTarget(target);
            } else {
                animate(attackImages);
                ((Towers)a).getHit(damage);
            }
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
}

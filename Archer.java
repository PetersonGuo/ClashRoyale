import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Troops
{
    protected Troops target; // The target of the archer
    private int imageNumber; // The number of images in the animation
    
    /**
     * Constructor for objects of class Archers
     * 
     * @param ally true if ally, false if enemy
     */
    public Archer(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 100; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 175;
        
        //attack stats
        damage = 1;
        size = 25;
        attackRange = 40 + size;
        detectionRange = 350;
        attackSound = new GreenfootSound("ArcherAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 3;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("WalkingArcher"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("ArcherAtk.png");
        attackImages[0].scale(size, size);
    
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Archers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(spawning){
            spawn();
        } else if(alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null){ // If there is a target
                moveTowardsTarget(troop);
            } else if(!crossedBridge){ //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if(isTouching(Bridge.class)){
                    crossBridge();
                }
            } else { //If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            }
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    public void addedToWorld(World w){
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a){ // Attack the target
        if (actCounter % attackSpeed <= attackSpeed / 5){ // If the attack counter is reached
            animate(attackImages);
            if(a instanceof Troops){ //If target is a troop
                shootArrowAtTarget();
                ((Troops)a).getHit(damage);
            }else if(a instanceof Towers){ //If target is a tower
                shootArrowAtTarget();
                ((Towers)a).getHit(damage);
            } 
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
    
    /**
     * Shoot an arrow at the target
     */
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target), getX(), getY());
        actCounter = 0;
    }
}

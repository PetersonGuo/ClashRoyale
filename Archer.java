import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Archers are a type of Troops, which can be either allies or enemies. The Archer's
 * behavior involves finding a target, moving towards it, and attacking it using a bow 
 * and arrow. If there is no target, the Archer will move towards the Towers class. 
 * The Archer has various stats, such as health, attack damage, and attack speed, as 
 * well as a SuperStatBar to display its health. The class includes methods for attacking
 * and shooting arrows at the target.
 * 
 * @author Kevin Luo, Isaac Chan
 * @version 1.0
 */
public class Archer extends Troops {
    protected Actor target; // The target of the archer
    private int imageNumber; // The number of images in the animation
    
    /**
     * Constructor for objects of class Archers
     * 
     * @param ally true if ally, false if enemy
     * @param hpMultiplyer the hp multiplyer
     * @param dmgMultiplyer the damage multiplyer
     */
    public Archer(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 50; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 25 * hpMultiplyer;
        
        //attack stats
        damage = 3 * dmgMultiplyer;
        size = 25;
        attackRange = 40 + size;
        attackSound = new Sound("ArcherAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 3;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("Archer"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("ArcherAtk.png");
        attackImages[0].scale(size, size);
    
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar((int)maxHealth, (int)currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Archers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        if (spawning)
            spawn();
        else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null) { // If there is a target
                moveTowardsTarget(troop);
            } else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class))
                    crossBridge();
            } else //If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            die();
        } else
            getWorld().removeObject(this);
    }
    
    /**
     * Added to world
     * 
     * @param w the world
     */
    public void addedToWorld(World w) {
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a) { // Attack the target
        animate(attackImages);
        if (actCounter % attackSpeed == 0) { // If the attack counter is reached
            target = a;
            if (a instanceof Troops && ((Troops)a).isAlive()) { //If target is a troop
                shootArrowAtTarget();
                ((Troops)a).getHit(damage);
            }else if (a instanceof Towers && ((Towers)a).isAlive()) { //If target is a tower
                shootArrowAtTarget();
                ((Towers)a).getHit(damage);
            } 
        } else if (actCounter % attackSpeed <= 10){
            setImage(walkImages[0]);
        }
    }
    
    /**
     * Shoot an arrow at the target
     */
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target, damage), getX(), getY());
        actCounter = 0;
    }
}

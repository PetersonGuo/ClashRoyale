import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Knights are a type of Troops, which can be either allies or enemies. The Knight's 
 * behavior involves finding a target on ground, moving towards it, and attacking it 
 * using an ax. If there is no target, the Knight will move towards the Towers class. 
 * The Knight has various stats, such as health, attack damage, and attack speed, as 
 * well as a SuperStatBar to display its health. The class includes methods for 
 * attacking at the target.
 * 
 * @author Isaac Chan, Kelby To
 * @version 1.0
 */
public class Knight extends Troops {
    /**
     * Constructor for objects of class Knight
     * 
     * @param ally Whether the tower is on the left or right side
     * @param hpMultiplyer The health multiplyer of the troop
     * @param dmgMultiplyer The damage multiplyer of the troop
     */
    public Knight(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 75; //the higher the number the slower the attacks
        animationSpeed = 12;
        
        //health stats
        currentHealth = maxHealth = 50 * hpMultiplyer;
        
        //attack stats
        damage = 6 * dmgMultiplyer;
        size = 25;
        attackRange = 15 + size;
        attackSound = new Sound("KnightAttack.mp3");

        //miscellaneous stats
        elixirCost = 3;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("KnightWalk"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("KnightAtk.png");
        attackImages[0].scale(size, size);
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar((int)maxHealth, (int)currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        if (spawning)
            spawn();
        else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null  && !((Troops)troop).isAir()) //If there is a target that is not air
                moveTowardsTarget(troop);
            else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class))
                    crossBridge();
            } else // If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            die();
        } else
            getWorld().removeObject(this);
    }
    
    /**
     * Method addedToWorld
     * 
     * @param w The world the object is added to
     */
    public void addedToWorld(World w) {
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * Attack method
     * 
     * @param a the actor to attack
     */
    public void attack(Actor a) {
        animate(attackImages);
        if (actCounter % attackSpeed == 0) { // If the attack counter is reached
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

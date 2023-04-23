import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Mini Pekkas are a type of Troops, which can be either allies or enemies. The Mini 
 * Pekka's behavior involves finding a target on ground, moving towards it, and 
 * attacking it using an ax. If there is no target, the Mini Pekka will move towards 
 * the Towers class. The Mini Pekka has various stats, such as health, attack damage, 
 * and attack speed, as well as a SuperStatBar to display its health. The class 
 * includes methods for attacking at the target.
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class MiniPekka extends Troops {
    /**
     * Constructor for objects of class MiniPekka
     * 
     * @param ally The team the troop is on
     * @param hpMultiplyer The hpMultiplyer of the troop
     * @param dmgMultiplyer The dmgMultiplyer of the troop
     */
    public MiniPekka(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 100; //the higher the number the slower the attacks
        animationSpeed = 25;
        
        //health stats
        currentHealth = maxHealth = 35 * hpMultiplyer;
        
        //attack stats
        damage = 12 * dmgMultiplyer;
        size = 30;
        attackRange = 15 + size;
        attackSound = new GreenfootSound("MiniPekkaAttack.mp3");

        //miscellaneous stats
        elixirCost = 4;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("PekkaWalk"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("PekkaAtk.png");
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
            if (troop != null && !((Troops)troop).isAir()) //If there is a target that is not air
                moveTowardsTarget(troop);
            else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class))
                    crossBridge();
            } else //If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            die();
        } else
            getWorld().removeObject(this);
    }
    
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
            if (a instanceof Troops) //If target is a troop
                ((Troops)a).getHit(damage);
            else if (a instanceof Towers) //If target is a tower
                ((Towers)a).getHit(damage);
        } else if (actCounter % attackSpeed <= 10)
            setImage(walkImages[0]);
    }
}


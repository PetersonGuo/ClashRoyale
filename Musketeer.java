import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Musketeers are a type of Troops, which can be either allies or enemies. 
 * The Musketeer's behavior involves finding a target, moving towards it, 
 * and attacking it using a musket. If there is no target, the Musketeer 
 * will move towards the Towers class. The Musketeer has various stats, 
 * such as health, attack damage, and attack speed, as well as a SuperStatBar
 * to display its health. The class includes methods for attacking and 
 * shooting pellets at the target.
 * 
 * @author Isaac Chan, Kevin Luo
 * @version 1.0
 */
public class Musketeer extends Troops {
    protected Actor target; //the target of the troop
    /**
     * @param ally whether the troop is on the player's side or not
     * @param hpMultiplyer the hpMultiplyer of the troop
     * @param dmgMultiplyer the dmgMultiplyer of the troop
     */
    public Musketeer(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 90;  //the higher the number the slower the attacks
        animationSpeed = 15;
        
        //health stats
        currentHealth = maxHealth = 30 * hpMultiplyer;
        
        //attack stats
        damage = 7 * dmgMultiplyer;
        size = 25;
        attackRange = 60 + size;
        attackSound = new Sound("MusketeerAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 4;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("Musketeer"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("MusketeerAtk.png");
        attackImages[0].scale(size, size);
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar((int)maxHealth, (int)currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Musketeer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        if (spawning)
            spawn();
        else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null) //If there is a target
                moveTowardsTarget(troop);
            else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class))
                    crossBridge();
            } 
            else //If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            die();
        } else
            getWorld().removeObject(this);
    } 
    
    /**
     * Added to world method
     * 
     * @param w the world the object is added to
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
        target = a;
        if (actCounter % attackSpeed == 0) { // If the attack counter is reached
            if (a instanceof Troops  && ((Troops)a).isAlive()) { //If target is a troop
                shootPelletAtTarget();
                ((Troops)a).getHit(damage);
            } else if (a instanceof Towers && ((Towers)a).isAlive()) { //If target is a tower
                shootPelletAtTarget();
                ((Towers)a).getHit(damage);
            }
        } else if (actCounter % attackSpeed <= 10)
            setImage(walkImages[0]);
    }
    
    /**
     * Shoot a pellet at the target
     */
    private void shootPelletAtTarget() {
        getWorld().addObject(new Pellet(target, damage), getX(), getY());
        actCounter = 0;
    }
}

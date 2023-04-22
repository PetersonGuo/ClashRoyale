import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musketeer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Musketeer extends Troops
{
    protected Actor target; //the target of the troop
    /**
     * Constructor for objects of class Musketeer
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Musketeer(boolean ally) {
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 75;
        animationSpeed = 15;
        
        //health stats
        currentHealth = maxHealth = 50;
        
        //attack stats
        damage = 3;
        size = 25;
        attackRange = 60 + size;
        detectionRange = 400;
        attackSound = new GreenfootSound("MusketeerAttack.mp3");
        
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
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    public void act()
    {
        super.act();
        if (spawning) {
            spawn();
        } else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null) { //If there is a target
                moveTowardsTarget(troop);
            }
            else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class)) {
                    crossBridge();
                }
            } 
            else{ //If there is no target
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
    public void attack(Actor a) { // Attack the target
        animate(attackImages);
        if (actCounter % attackSpeed <= 5) { // If the attack counter is reached
            target = a;
            if (a instanceof Troops) { //If target is a troop
                shootPelletAtTarget();
                ((Troops)a).getHit(damage);
            }else if (a instanceof Towers) { //If target is a tower
                shootPelletAtTarget();
                ((Towers)a).getHit(damage);
            }
        } else if (actCounter % attackSpeed <= 10){
            setImage(walkImages[0]);
        }
    }
    
    /**
     * Shoot a pellet at the target
     */
    private void shootPelletAtTarget() {
        getWorld().addObject(new Pellet(target), getX(), getY());
        actCounter = 0;
    }
}

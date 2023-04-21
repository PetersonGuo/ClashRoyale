import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musketeer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Musketeer extends Troops {
    protected Troops target; //the target of the troop
    
    /**
     * Constructor for objects of class Musketeer
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Musketeer(boolean ally){
        super(ally);
        
        //stats
        maxSpeed = 5;
        attackSpeed = 900;
        animationSpeed = 20;
        currentHealth = maxHealth = 14;
        damage = 8;
        attackRange = 60;
        elixirCost = 4;
        air = false;
        
        setImage("musketeer.png");
        
        walkImages = new GreenfootImage[3];
        
        walkImages[0] = new GreenfootImage("musketeer 0.png");
        walkImages[1] = new GreenfootImage("musketeer 1.png");
        walkImages[2] = new GreenfootImage("musketeer 2.png");
        attackImages[0] = new GreenfootImage("musketeer attack.png");
    }
    
    /**
     * Act - do whatever the Musketeer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        if (findTarget(Troops.class) != null)
            moveTowardsTarget(findTarget(Troops.class));
        else
            moveTowardsTarget(findTarget(Towers.class));
    }
    
    /**
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a) {
        if (actCounter % attackSpeed == 0) {
            Troops target = (Troops)findTarget(Troops.class);
            if (target != null)
                moveTowardsTarget(target);
        }
    }
    
    /**
     * Shoot a pellet at the target
     */
    private void shootpelletAtTarget() {
        getWorld().addObject(new Pellet(target), getX(), getY());
        actCounter = 0;
    }
}

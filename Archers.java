import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archers extends Troops {
    protected Troops target; // The target of the archer
    private int imageNumber; // The number of images in the animation
    
    /**
     * Constructor for objects of class Archers
     * 
     * @param ally true if ally, false if enemy
     */
    public Archers(boolean ally) {
        super(ally);
        
        //stats
        maxSpeed = 5;
        attackSpeed = 60;
        animationSpeed = 10;
        currentHealth = 10;
        damage = 4;
        attackRange = 40;
        elixirCost = 3;
        air = false;
        
        setImage("new archer.png");
        
        walkImages = new GreenfootImage[3]; // The images for the walking animation
        
        walkImages[0] = new GreenfootImage("walking archer 0.png");
        walkImages[1] = new GreenfootImage("walking archer 1.png");
        walkImages[2] = new GreenfootImage("walking archer 2.png");
        attackImages[0] = new GreenfootImage("archer attack.png");
    }
    
    /**
     * Act - do whatever the Archers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        
        if (findTarget(Troops.class) != null) // If there is a target
            moveTowardsTarget(findTarget(Troops.class));
        else // If there is no target
            moveTowardsTarget(findTarget(Towers.class));
    }
    
    /**
     * Attack the target
     * 
     * @param a the target
     */
    public void attack(Actor a) { // Attack the target
        if (actCounter % attackSpeed == 0) { // If the attack counter is reached
            Troops target = (Troops)findTarget(Troops.class);
            shootArrowAtTarget();
            if (target != null) // If there is a target
                moveTowardsTarget(target);
        }
    }
    
    /**
     * The animation for the archer
     * @return the animation images
     */
    public GreenfootImage[] animation() { // The animation for the archer
        GreenfootImage[] images = new GreenfootImage[imageNumber];
        for(int i = 0; i < images.length; i++) // For each image
            images[i] = new GreenfootImage("walking archer " + i + ".png");
        return images;
    }
    
    /**
     * Shoot an arrow at the target
     */
    private void shootArrowAtTarget() { 
        getWorld().addObject(new Arrow(target), getX(), getY());
        actCounter = 0;
    }
}

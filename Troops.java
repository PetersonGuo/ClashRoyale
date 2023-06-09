import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * This is a Java code that defines an abstract class named Troops. This class extends the Actor class of the Greenfoot 
 * library and provides the basic functionalities and properties that any troop in a game could have. The Troops class 
 * also provides two protected methods, findTarget(Class<T> c) and moveTowardsTarget(Actor a). The findTarget(Class<T> c) 
 * method takes a Class<T> object as an argument and returns an Actor object. The moveTowardsTarget(Actor a) method takes 
 * an Actor object as an argument and moves the troop towards that object. Both of these methods are used to search for new 
 * targets and move the troops towards them. By moving towards a target, the troop then has a choice of attacking it.
 * 
 * @author Isaac Chan 
 * @version 1.0
 */
public abstract class Troops extends Actor {
    protected double speed, maxSpeed, attackSpeed, animationSpeed, direction; //speed is in pixels per act
    protected int distX, distY; //distance between the troop and the target
    protected boolean crossedBridge = false; //true if the troop has crossed the bridge, false if not
    
    protected double maxHealth, currentHealth, damage, hpMultiplyer, dmgMultiplyer;
    protected int attackRange, detectionRange = 100; //attack range is in pixels

    protected int elixirCost; //cost of the troop in elixir
    protected boolean air, ally, spawning = true, alive = true; //air is true if the troop can fly over the bridge, ally is true if the troop is on the player's side
    
    protected int size; //size of the health bar
    protected Color filledColor = new Color (0, 255, 0);  //green
    protected Color missingColor = new Color (255, 0, 0); //red
    protected SuperStatBar healthBar; //health bar
    
    protected int actCounter = 0, currentImage = 0; //actCounter is used to determine when to change the image, currentImage is the current image being displayed
    protected GreenfootImage[] walkImages; //images for walking
    protected GreenfootImage[] attackImages; //images for attacking
    
    protected Sound spawnSound, dieSound, attackSound; //sounds for spawning, dying, and attacking
    
    /**
     * Constructor for objects of class Troops
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Troops(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        this.ally = ally;
        this.hpMultiplyer = hpMultiplyer;
        this.dmgMultiplyer = dmgMultiplyer;
    }
    
    /**
     * Act - do whatever the Troops wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        actCounter++;
    }
    
    /**
     * Called when the troop is added to the world
     * @param w the world the troop is added to
     */
    public void addedToWorld(World w) {
        spawnSound = new Sound("spawn.mp3");
        spawnSound.play();
    }
    
    /**
     * Find the troop
     * @param <T> the class of the troop
     * @param c the class of the troop
     * @return the troop
     */
    protected <T> Actor findTarget(Class<T> c) { //runs every act to search for new targets
        Actor target = null;
        List<T> actors = null;
        if (c == Troops.class){ // finding troops
            actors = getObjectsInRange(detectionRange, c);
            Iterator<T> iter = actors.iterator();
            while (iter.hasNext()) { //checks if is ally
                T actor = iter.next();
                if (((Troops) actor).isAlly() == isAlly()) {
                    iter.remove();
                }
            }
        }else if (c == Bridge.class)  // finding bridges
            actors = getWorld().getObjects(c);        
        else if (c == Towers.class) { // find towers
            actors = new ArrayList<>();
            for (T tower : getWorld().getObjects(c)) { // Adds any towers that are allies
                if (((Towers)tower).isAlly() == ally)
                    actors.add(tower);
            }
        }
        target = nearestTarget(actors); 
        return target;
    }
    
    /**
     * Move towards the target
     * @param a the target
     */
    protected void moveTowardsTarget(Actor a) {
        int targetX = a.getX(), targetY = a.getY(); // targetX and targetY are the x and y location of the target
        
        //rotate towards the target
        distX = targetX - getX();
        distY = targetY - getY();
        
        if (distX != 0) {
            if (distX >= 0 && distY >= 0) { //first quadrant
                direction = Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if (distX <= 0 && distY >= 0) { //second quadrant
                direction = 180 - Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if (distX <= 0 && distY <= 0) { //third quadrant
                direction = 180 + Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if (distX >= 0 && distY <= 0) { //fourth quadrant
                direction = 360 - Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            }
        }
        
        setRotation((int)direction);
        
        if (distFromTarget(a) <= attackRange && a.getClass() != Bridge.class) { //within attack range, attack
            attack(a);
            if(actCounter % attackSpeed == 0){
                attackSound.play();
            }
        } else { //move towards the target
            animate(walkImages);
            move((int)speed);
        }
    }
    
    /**
     * Return the nearest target
     * @param a the target
     */
    protected <T> Actor nearestTarget(List<T> actors) {
        Actor target = null;
        if (actors.size() > 0) { //if there is a target found within the range
            target = (Actor)actors.get(0);
            for(T actor : actors) { // finds the closest troop as a target
                if (distFromTarget((Actor)actor) < distFromTarget(target)) {
                    target = (Actor)actor;
                }
            }
        }
        return target;
    }
    
    /**
     * Move across the bridge
     */
    protected void crossBridge() { //once the troop touches the bridge
        Bridge b = (Bridge)getOneIntersectingObject(Bridge.class);
        if (ally) {
            setRotation(90);
            move((int)speed);
            if (getY() < b.getY()) { //pass the bridge from the ally side
                crossedBridge = true;
            }
        } else {
            setRotation(-90);
            move((int)speed);
            if (getY() > b.getY()) { //pass the bridge from the enemy side
                crossedBridge = true;
            }
        }
    }
    
    /**
     * Once the troop has spawned
     */
    protected void spawn() { //when the troop has spawned
        World w = getWorld();
        alive = true;
        if (actCounter <= 60) { //if the troop hasn't existed for 1 second
            speed = 0;
            if (ally) {
                direction = 90;
            } else {
                direction = -90;
            }
            setRotation((int)direction);
        } else { //allows the troop to move
            spawning = false;
            speed = maxSpeed;
        }
    }
    
    /**
     * Get the distance from the target
     * @param a the target
     * @return the distance from the target
     */
    protected double distFromTarget(Actor a) {
        return Math.sqrt(Math.pow(a.getX() - getX(), 2) + Math.pow(a.getY() - getY(), 2));
    }
    
    /**
     * Die method for the troop
     * Removes the troop and adds a dead body
     */
    protected void die() {
        if (currentHealth <= 0) {
            dieSound = new Sound("death.mp3");
            dieSound.play();
            alive = false;
            getWorld().addObject(new Dead(size), getX(), getY());
        }
    }
    
    /**
     * Animate the troop
     * @param images the images to animate
     */
    protected void animate(GreenfootImage[] images) {
        if (actCounter % animationSpeed == 0) {
            //change the image to the next frame
            if (images.length != 1) {
                currentImage++;
            }
            if (currentImage >= images.length) {
                currentImage = 0;
            }
            setImage(images[currentImage]); //set the image to the new frame
            setRotation((int)direction);
        }
    }
    
    /**
     * Take damage
     * @param damage the damage aken deal
     */
    public void getHit(double damage) {
        currentHealth -= damage;
        healthBar.update((int)currentHealth);
    }
    
    /**
     * Check if the troop is an ally
     * @return true if the troop is an ally, false if not
     */
    public boolean isAlly() {
        return ally;
    }
    
    /**
     * Check if the troop is an ally
     * @return true if the troop is an ally, false if not
     */
    public boolean isAir() {
        return air;
    }
    
    /**
     * Check if the troop is alive
     * @return true if the troop is alive, false if not
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * Attack the target
     * @param a the target
     */
    protected abstract void attack(Actor a);
}

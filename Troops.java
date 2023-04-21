import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Troops here.
 * 
 * @author Isaac Chan 
 * @version (a version number or a date)
 */
public abstract class Troops extends Actor {
    protected double speed, maxSpeed, attackSpeed, animationSpeed, direction; //speed is in pixels per act
    protected int distX, distY; //distance between the troop and the target
    protected boolean crossedBridge = true; //true if the troop has crossed the bridge, false if not
    
    protected int maxHealth, currentHealth, damage, attackRange, detectionRange; //attack range is in pixels

    protected int elixirCost; //cost of the troop in elixir
    protected boolean air, ally, spawning, alive = true; //air is true if the troop can fly over the bridge, ally is true if the troop is on the player's side
    
    protected int size; //size of the health bar
    protected Color filledColor = new Color (0, 255, 0);  //green
    protected Color missingColor = new Color (255, 0, 0); //red
    protected SuperStatBar healthBar; //health bar
    
    protected boolean turned = false; //true if the troop has turned towards the target, false if not
    protected int actCounter = 0, currentImage = 0; //actCounter is used to determine when to change the image, currentImage is the current image being displayed
    protected GreenfootImage[] walkImages; //images for walking
    protected GreenfootImage[] attackImages; //images for attacking
    protected World world; //the world the troop is in
    
    protected GreenfootSound spawn, die, attack; //sounds for spawning, dying, and attacking
    
    /**
     * Constructor for objects of class Troops
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Troops(boolean ally){
        this.ally = ally;
        world = getWorld();
    }
    
    public void addedToWorld(World w){
        spawning = true;
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
        if (c == Troops.class) //finding troops
            actors = getObjectsInRange(detectionRange, c);
        else if (c == Bridge.class) //finding bridges
            actors = world.getObjects(c);        
        else if (c == Towers.class) { //find towers
            actors = world.getObjects(c);
            for (T tower : actors) //removes any towers that are allies
                if (((Towers)tower).isAlly() == isAlly())
                    actors.remove(tower);
        }
        target = nearestTarget(actors); 
        return target;
    }
    
    /**
     * Move towards the target
     * @param a the target
     */
    protected void moveTowardsTarget(Actor a) {
        int targetX = a.getX(), targetY = a.getY(); //targetX and targetY are the x and y location of the target
        if (!turned) { 
            //rotate towards the target
            distX = getX() - targetX;
            distY = getY() - targetY;
            direction = Math.atan(distY/distX);
            setRotation((int)direction);
            turned = true;
        } else {
            if (distFromTarget(a) <= attackRange && a.getClass() != Bridge.class) //within attack range, attack
                attack(a);
            else { //move towards the target
                animate(walkImages);
                move((int)speed);
            }
            turned = false; //allows the troop to turn again if the target has moved
        }
    }
    
    /**
     * Reuturn the nearest target
     * @param a the target
     */
    protected <T> Actor nearestTarget(List<T> actors) {
        Actor target = null;
        if(actors.size() > 0) { //if there is a target found within the range
            target = (Actor)actors.get(0);
            for(T actor : actors) //finds the closest troop as a target
                if (distFromTarget((Actor)actor) < distFromTarget(target))
                    target = (Actor)actor;
        }
        return target;
    }
    
    /**
     * Move across the bridge
     */
    protected void crossBridge() { //once the troop touches the bridge
        Bridge b = (Bridge)getOneIntersectingObject(Bridge.class);
        if (ally) {
            setRotation(0);
            move((int)speed);
            if (getY() < b.getY() - b.getImage().getHeight()/2) //pass the bridge from the ally side
                crossedBridge = true;
        } else {
            setRotation(180);
            move((int)speed);
            if (getY() > b.getY() + b.getImage().getHeight()/2) //pass the bridge from the enemy side
                crossedBridge = true;
        }
    }
    
    /**
     * Spawn the troop
     */
    protected void spawn() { //when the troop has spawned
        alive = true;
        if (actCounter <= 60) { //if the troop hasn't existed for 1 second
            speed = 0;
            if (ally)
                direction = 0;
            else
                direction = 180;
        } else {
            spawning = false;
            speed = maxSpeed;
        }
    }
    
    /**
     * Get the distance from the target
     * @param a the target
     * @return the distance from the target
     */
    protected double distFromTarget(Actor a){
        return Math.sqrt(Math.pow(a.getX() - getX(), 2) + Math.pow(a.getY() - getY(), 2));
    }
    
    protected void die() { //when this troop dies
        if (currentHealth <= 0) {
            die.play();
            alive = false;
        }
    }
    
    /**
     * Animate the troop
     * @param images the images to animate
     */
    protected void animate(GreenfootImage[] images) {
        if (actCounter % animationSpeed == 0) {
            //change the image to the next frame
            currentImage++;
            if (currentImage > images.length)
                currentImage = 0;
             setImage(images[currentImage]); //set the image to the new frame
        }
    }
    
    /**
     * Resize the image
     * @param image the image to resize
     * @param size the size to resize the image to
     */
    protected void resize(GreenfootImage image, int size){
        image.scale(size, size);
    }
    
    /**
     * Hit the target
     * @param damage the damage to deal
     */
    public void getHit(int damage){
        currentHealth -= damage;
        healthBar.update(currentHealth);
    }
    
    /**
     * Check if the troop is an ally
     * @return true if the troop is an ally, false if not
     */
    public boolean isAlly(){
        return ally;
    }
    
    /**
     * Check if the troop is alive
     * @return true if the troop is alive, false if not
     */
    public boolean isAlive(){
        return alive;
    }
    
    /**
     * Attack the target
     * @param a the target
     */
    protected abstract void attack(Actor a);
}

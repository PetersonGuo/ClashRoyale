import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Troops here.
 * 
 * @author Isaac Chan 
 * @version (a version number or a date)
 */
public abstract class Troops extends Actor
{
    //movement variables
    protected double speed, maxSpeed, attackSpeed, animationSpeed, direction;
    protected int distX, distY;
    protected boolean crossedBridge = false;
    
    //attack variables
    protected int maxHealth, currentHealth, damage, attackRange, detectionRange;

    //spawning variables
    protected int elixirCost;
    protected boolean air, ally, spawning = true, alive = true;
    
    //health bar variables
    protected int size;
    protected Color filledColor = new Color (0, 255, 0); //green
    protected Color missingColor = new Color (255, 0, 0); //red
    protected SuperStatBar healthBar;
    
    protected boolean turned = false;
    protected int actCounter = 0, currentImage = 0;
    protected GreenfootImage[] walkImages;
    protected GreenfootImage[] attackImages;
    
    //sounds
    protected GreenfootSound spawnSound, dieSound, attackSound;
    
    public Troops(boolean ally){
        this.ally = ally;
    }
    
    public void act(){
        actCounter++;
    }
    
    public void addedToWorld(World w){
        spawnSound = new GreenfootSound("spawn.mp3");
        spawnSound.play();
    }
    
    protected <T> Actor findTarget(Class<T> c) { //runs every act to search for new targets
        Actor target = null;
        List<T> actors = null;
        if(c == Troops.class){ //finding troops
            actors = getObjectsInRange(detectionRange, c);
        } else if (c == Bridge.class){ //finding bridges
            actors = getWorld().getObjects(c);        
        } else if (c == Towers.class){ //find towers
            actors = getWorld().getObjects(c);
            for(T tower : actors){ //removes any towers that are allies
                if(((Towers)tower).isAlly() == isAlly()){
                    actors.remove(tower);
                }
            }
        }
        target = nearestTarget(actors); 
        return target;
    }
    
    protected void moveTowardsTarget(Actor a){
        //x and y location of the target
        int targetX = a.getX();
        int targetY = a.getY();
        
        //rotate towards the target
        distX = targetX - getX();
        distY = targetY - getY();
        
        if(distX != 0){
            if(distX >= 0 && distY >= 0){ //first quadrant
                direction = Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if(distX <= 0 && distY >= 0){ //second quadrant
                direction = 180 - Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if(distX <= 0 && distY <= 0){ //third quadrant
                direction = 180 + Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            } else if(distX >= 0 && distY <= 0){ //fourth quadrant
                direction = 360 - Math.atan(Math.abs(distY/distX)) * 180 / Math.PI;
            }
        }
        
        setRotation((int)direction);
            
        if(distFromTarget(a) <= attackRange && a.getClass() != Bridge.class){ //within attack range, attack
            attack(a);
            attackSound.play();
        } else { //move towards the target
            animate(walkImages);
            move((int)speed);
        }
    }
    
    protected <T> Actor nearestTarget(List<T> actors){
        Actor target = null;
        if(actors.size() > 0) { //if there is a target found within the range
            target = (Actor)actors.get(0);
            for(T actor : actors){ //finds the closest troop as a target
                if(distFromTarget((Actor)actor) < distFromTarget(target)){
                    target = (Actor)actor;
                }
            }
        }
        return target;
    }
    
    protected void crossBridge(){ //once the troop touches the bridge
        Bridge b = (Bridge)getOneIntersectingObject(Bridge.class);
        if(ally){
            setRotation(-90);
            move((int)speed);
            if(getY() < b.getY()){ //pass the bridge from the ally side
                crossedBridge = true;
            }
        } else {
            setRotation(90);
            move((int)speed);
            if(getY() > b.getY()){ //pass the bridge from the enemy side
                crossedBridge = true;
            }
        }
    }
    
    protected void spawn(){ //when the troop has spawned
        alive = true;
        if(actCounter <= 60){ //if the troop hasn't existed for 1 second
            speed = 0;
            if(ally){
                direction = -90;
            } else {
                direction = 90;
            }
            setRotation((int)direction);
        } else {
            spawning = false;
            speed = maxSpeed;
        }
    }
    
    protected double distFromTarget(Actor a){
        return Math.sqrt(Math.pow(a.getX() - getX(), 2) + Math.pow(a.getY() - getY(), 2));
    }
    
    protected void die(){ //when this troop dies
        if(currentHealth <= 0){
            dieSound = new GreenfootSound("death.mp3");
            dieSound.play();
            alive = false;
        }
    }
    
    protected void animate(GreenfootImage[] images){
        if(actCounter % animationSpeed == 0){
            //change the image to the next frame
            if(images.length != 1){
                currentImage++;
            }
            if(currentImage >= images.length){
                currentImage = 0;
            }
            setImage(images[currentImage]); //set the image to the new frame
            setRotation((int)direction);
        }
    }
    
    protected void resize(GreenfootImage image, int size){
        image.scale(size, size);
    }
    
    public void getHit(int damage){
        currentHealth -= damage;
        healthBar.update(currentHealth);
    }
    
    public boolean isAlly(){
        return ally;
    }
    
    public boolean isAir(){
        return air;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    protected abstract void attack(Actor a);
}

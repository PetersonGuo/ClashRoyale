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
    protected boolean crossedBridge = true;
    
    //attack variables
    protected int maxHealth, currentHealth, damage, attackRange, detectionRange;

    //spawning variables
    protected int elixirCost;
    protected boolean air, ally, spawning, alive = true;
    
    //health bar variables
    protected int size;
    protected Color filledColor = new Color (0, 255, 0); //green
    protected Color missingColor = new Color (255, 0, 0); //red
    protected SuperStatBar healthBar;
    
    protected boolean turned = false;
    protected int actCounter = 0, currentImage = 0;
    protected GreenfootImage[] walkImages;
    protected GreenfootImage[] attackImages;
    protected World world;
    
    //sounds
    protected GreenfootSound spawn, die, attack;
    
    public Troops(boolean ally){
        this.ally = ally;
        world = getWorld();
    }
    
    public void addedToWorld(World w){
        spawning = true;
    }
    
    protected <T> Actor findTarget(Class<T> c) { //runs every act to search for new targets
        Actor target = null;
        List<T> actors = null;
        if(c == Troops.class){ //finding troops
            actors = getObjectsInRange(detectionRange, c);
        } else if (c == Bridge.class){ //finding bridges
            actors = world.getObjects(c);        
        } else if (c == Towers.class){ //find towers
            actors = world.getObjects(c);
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
        if(!turned){ 
            //rotate towards the target
            distX = getX() - targetX;
            distY = getY() - targetY;
            direction = Math.atan(distY/distX);
            setRotation((int)direction);
            turned = true;
        } else {
            if(distFromTarget(a) <= attackRange && a.getClass() != Bridge.class){ //within attack range, attack
                attack(a);
            } else { //move towards the target
                animate(walkImages);
                move((int)speed);
            }
            turned = false; //allows the troop to turn again if the target has moved
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
            setRotation(0);
            move((int)speed);
            if(getY() < b.getY() - b.getImage().getHeight()/2){ //pass the bridge from the ally side
                crossedBridge = true;
            }
        } else {
            setRotation(180);
            move((int)speed);
            if(getY() > b.getY() + b.getImage().getHeight()/2){ //pass the bridge from the enemy side
                crossedBridge = true;
            }
        }
    }
    
    protected void spawn(){ //when the troop has spawned
        alive = true;
        if(actCounter <= 60){ //if the troop hasn't existed for 1 second
            speed = 0;
            if(ally){
                direction = 0;
            } else {
                direction = 180;
            }
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
            die.play();
            alive = false;
        }
    }
    
    protected void animate(GreenfootImage[] images){
        if(actCounter % animationSpeed == 0){
            //change the image to the next frame
            currentImage++;
            if(currentImage > images.length){
                currentImage = 0;
            }
             setImage(images[currentImage]); //set the image to the new frame
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
    
    public boolean isAlive(){
        return alive;
    }
    
    protected abstract void attack(Actor a);
}

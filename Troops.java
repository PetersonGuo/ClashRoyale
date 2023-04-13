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
    protected double speed, attackSpeed, direction;
    protected int distX, distY;
    protected boolean crossedBridge = false;
    
    //attack variables
    protected int health, damage, attackRange, detectionRange;

    //spawning variables
    protected int elixerCost, spawnTimer = 60;
    
    protected boolean air, ally, spawning, alive = true, turned = false;
    protected GreenfootImage image;
    
    //sounds
    protected GreenfootSound spawn, die;
    
    public Troops(boolean ally){
        this.ally = ally;
    }
    
    public void act()
    {
        if(spawning){
            spawn();
        }
        if(findTarget(Troops.class) != null){
            moveTowardsTarget(findTarget(Troops.class));
        } else if (!crossedBridge){
            moveTowardsTarget(findTarget(Bridge.class));
            if(isTouching(Bridge.class)){
                crossBridge();
            }
        } else {
            moveTowardsTarget(findTarget(Towers.class));
        }
    }
    
    public void addedToWorld(World w){
        spawning = true;
    }
    
    private <T> Actor findTarget(Class<T> c) { //runs every act to search for new targets
        List<T> actors = getObjectsInRange(detectionRange, c);
        Actor target = null;
        //find the nearest target
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
    
    private void moveTowardsTarget(Actor a){
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
                move((int)speed);
            }
            turned = false; //allows the troop to turn again if the target has moved
        }
    }
    
    private void crossBridge(){
        Bridge b = (Bridge)getOneIntersectingObject(Bridge.class);
        if(ally){
            setRotation(0);
            move((int)speed);
            if(getY() < b.getY() - b.getImage().getHeight()/2){
                crossedBridge = true;
            }
        } else {
            setRotation(180);
            move((int)speed);
            if(getY() > b.getY() + b.getImage().getHeight()/2){
                crossedBridge = true;
            }
        }
    }
    
    private void spawn(){ //when the troop has spawned
        alive = true;
        if(spawnTimer >= 0){
            speed = 0;
            if(ally){
                direction = 0;
            } else {
                direction = 180;
            }
            spawnTimer--;
        } else {
            spawning = false;
        }
    }
    
    private double distFromTarget(Actor a){
        return Math.sqrt(Math.pow(a.getX() - getX(), 2) + Math.pow(a.getY() - getY(), 2));
    }
    
    private void die(){ //when this troop dies
        if(health <= 0){
            die.play();
            alive = false;
        }
    }
    
    public void getHit(int damage){
        health -= damage;
    }
    
    public boolean isAlly(){
        return ally;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    protected abstract void attack(Actor a);
    
    /**
     * once the tower is found, only attack the tower
     */
}

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
   
    //attack variables
    protected int health, damage, attackRange, radius = 0;

    //spawning variables
    protected int spawnTimer = 60;
   
    protected boolean air, ally, spawning, alive = true, foundTarget = false;
    protected GreenfootImage image;
   
    //sounds
    protected GreenfootSound spawn, die;
   
    public Troops(boolean ally){
        this.ally = ally;
    }

    public void addedToWorld(World w){
        spawning = true;
    }
   
    private<T> void findTarget(Class<T> c) {
        List<T> actors = getObjectsInRange(radius, c);
        Actor target = null;
        //find the nearest target
        for(T a: actors){ //checks to see if the objects within the radius are allies
            if(!((Troops)a).isAlly()) { //if the target is an enemy troop
                target = (Troops)a;
            } else if (!((Towers)a).isAlly()) { //if the target is an enemy tower
                target = (Towers)a;
            }
        }
        if(target != null){ //when a target is found
            //find the targets location
            int locationX = ((Actor)target).getX();
            int locationY = ((Actor)target).getY();
           
            moveTowardsTarget(locationX, locationY, (Actor)target);
        } else { //if a target is not found
            radius++; //increase the detection range until a target is found
        }
    }
   
    private void moveTowardsTarget(int locationX, int locationY, Actor a){
        if(!foundTarget){
            //rotate towards the target
            distX = getX() - locationX;
            distY = getY() - locationY;
            direction = Math.atan(distY/distX);
            setRotation((int)direction);
            foundTarget = true;
        } else {
            //move towards the target
            setLocation((int)(getX() - distX/speed), (int)(getY() - distY/speed));
            List target = getObjectsInRange(attackRange, a.getClass());
            if(target.contains(a)){ //if close enough to the target to attack
                attack(a);
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
}
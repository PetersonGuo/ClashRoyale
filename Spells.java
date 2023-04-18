import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Spells here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spells extends Actor
{
    protected int speed, damage, elixer, areaOfEffect, targetX, targetY;
    protected boolean ally;
    protected Troops target;
    protected Towers target2;
    
    protected GreenfootImage image;
    
    public Spells(boolean ally){
        this.ally = ally;
    }
    
    public void addedToWorld (World w){
        ArrayList<Troops> troopsToHit = (ArrayList<Troops>) w.getObjects(Troops.class);
        ArrayList<Towers> towersToHit = (ArrayList<Towers>) getWorld().getObjects(Towers.class);
        if (troopsToHit.size() > 0){ //if there are troops, turn towards troops first
            targetX = troopTarget().getX();
            targetY = troopTarget().getY();
            turnTowards(targetX, targetY);
        }else{ //if no troops, turn and target towers
            targetX = towerTarget().getX();
            targetY = towerTarget().getY();
            turnTowards(targetX, targetY);
        }
    }
    
    public Troops troopTarget(){
        ArrayList<Troops> troopsToHit = (ArrayList<Troops>) getWorld().getObjects(Troops.class);
        Troops closest = null;
        closest = troopsToHit.get(0);
        // find the troop cloest to the tower to target
        for(Troops t : troopsToHit){
            if(getDistanceFromTower(t) > getDistanceFromTower(closest)){
                closest = t;
            }
        }
        return closest;
    }
    
    public Towers towerTarget(){
        ArrayList<Towers> towersToHit = (ArrayList<Towers>) getWorld().getObjects(Towers.class);
        Towers lowest = null;
        lowest = towersToHit.get(0);
        //check for the lowest hp tower to target
        return lowest;
    }
    
    private double getDistanceFromTower(Troops t){
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void damage(){
        for(Troops enemy : getObjectsInRange(areaOfEffect, Troops.class)){
            enemy.health -= damage * 2;
        }
        /*
        for(Tower tower : getObjectsInRange(areaOfEffect, Tower.class))
            tower.hp -= damage;
        */
    }
    
    protected void hit(){
        if(intersects(target)){
            //hit
            damage();
            getWorld().removeObject(this);
        }
    }
}

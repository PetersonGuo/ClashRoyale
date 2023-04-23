import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * The code is a Java class called Towers which represents a tower in a game. 
 * It has properties such as ally/enemy, range, hp, damage, and a Troops target. 
 * It also has methods to find a target, shoot an arrow at the target, and get 
 * the distance from the tower to a troop. The class also keeps track of the 
 * number of ally and enemy towers and can reset these numbers.
 * 
 * @author Kelby To 
 * @version 1.0
 */
public abstract class Towers extends Actor {
    protected boolean ally; // true if ally, false if enemy
    protected GreenfootImage image; //the image of the tower
    protected int range, shootingCooldown, actsSinceShooting;  //range is the radius of the tower
    protected double hp, damage, hpMultiplyer, dmgMultiplyer;
    protected Troops target; //the troop to target
    protected SuperStatBar hpBar;
    protected GreenfootSound destroyedSound;
    protected boolean alive;
    
    protected static int numAllyTowers = 0;
    protected static int numEnemyTowers = 0;
    
    /**
     * Constructor for objects of class Towers
     * 
     * @param ally true if ally, false if enemy
     */
    public Towers(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        alive = true;
        this.ally = ally;
        this.hpMultiplyer = hpMultiplyer;
        this.dmgMultiplyer = dmgMultiplyer;
        if(ally)
            numAllyTowers++;
        else
            numEnemyTowers++;
    }
    
    /**
     * Added to world event
     * @param w the world the tower is added to
     */
    public void addedToWorld(World w){
        w.addObject(hpBar, 0, 0);
    }
    
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        actsSinceShooting++;
        if (target != null && target.isAlive()){
            if (actsSinceShooting >= shootingCooldown)shootArrowAtTarget();
        }else
            target = findTarget();
    }
    
    /**
     * Finds the closest target
     * 
     * @return the closest target
     */
    private Troops findTarget() {
        //find all troops with in the tower's range
        ArrayList<Troops> troopsInRange = (ArrayList<Troops>)getObjectsInRange(range, Troops.class); 
        Troops closest = null;
        if (troopsInRange.size() > 0) {
            closest = troopsInRange.get(0);
            for(Troops t : troopsInRange) {
                if (getDistanceFromTower(t) > getDistanceFromTower(closest) && closest.isAlly() == ally)
                    closest = t;
            }
            if(closest.isAlly() != ally) closest = null;
        }
        return closest;
    }
    
    /**
     * Shoots an arrow at the target
     */
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target, dmgMultiplyer), getX(), getY());
        actsSinceShooting = 0;
    }
    
    /**
     * Damage the Tower
     * 
     * @param dmg the damage the troop does
     */
    public abstract void getHit(double dmg);
 
    /**
     * Gets the hp of the tower
     * 
     * @return the hp of the tower
     */
    public double getHp() {
        return hp;
    }
    
    /**
     * Gets the distance from the tower to the troop
     * 
     * @param t the troop to get the distance from
     * @return the distance from the tower to the troop
     */
    private double getDistanceFromTower(Troops t) {
        //use pythagorean theorem to find the distance between the troop and the tower
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    /**
     * Gets whether the tower is an ally
     * 
     * @return true if ally, false if enemy
     */
    public boolean isAlly() {
        return ally;
    }
    
    /**
     * Gets whether the tower is alive
     * 
     * @return true if alive, false if dead
     */
    public boolean isAlive(){
        return alive;
    }
    
    /**
     * Gets the number of towers on each side
     * 
     * @return an array with the number of towers on each side
     */
    public static int[] getCrowns() {
        return new int[]{3-numEnemyTowers, 3-numAllyTowers};
    }
    
    /**
     * Resets the number of towers on each side
     */
    public static void resetTowers(){
        numAllyTowers = 0;
        numEnemyTowers = 0;
    }
}

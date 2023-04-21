import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Towers will shoot any enemy targets within its range
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public abstract class Towers extends Actor
{
    protected boolean ally;
    protected GreenfootImage image;
    protected int hp;
    protected int range;
    protected Troops target;
    protected int shootingCooldown;
    protected int actsSinceShooting;
    
    public Towers(boolean ally) {
        this.ally = ally;
    }
    
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actsSinceShooting++;
        if (target != null) {
            //has target
            if (actsSinceShooting >= shootingCooldown)shootArrowAtTarget();
        }else{
            //find target if there isn't one
            target = findTarget();
        }
    }
    
    private Troops findTarget() {
        //find all troops with in the tower's range
        ArrayList<Troops> troopsInRange = (ArrayList<Troops>)getObjectsInRange(range, Troops.class); 
        Troops closest = null;
        if (troopsInRange.size() > 0) {
            closest = troopsInRange.get(0);
            for(Troops t : troopsInRange) {
                //cycle through all troops in range and find the closest one
                if (getDistanceFromTower(t) > getDistanceFromTower(closest)) {
                    closest = t;
                }
            }
        }
        return closest;
    }
    
    private void shootArrowAtTarget() {
        getWorld().addObject(new Arrow(target), getX(), getY());
        actsSinceShooting = 0;
    }
    
    public void getHit(int dmg) {
        hp -= dmg;
        //tower destroyed
        if(hp <= 0) getWorld().removeObject(this);
    }
    
    public int getHp(){
        return hp;
    }
    
    private double getDistanceFromTower(Troops t) {
        //use pythagorean theorem to find the distance between the troop and the tower
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
    
    public boolean isAlly() {
        return ally;
    }
}

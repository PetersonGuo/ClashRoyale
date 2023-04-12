import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Towers here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class Towers extends Actor
{
    protected boolean ally;
    protected GreenfootImage image;
    protected int range;
    protected Troops target;
    
    public Towers(boolean ally){
        this.ally = ally;
    }
    
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        ArrayList<Troops> troopsInRange = (ArrayList<Troops>)getObjectsInRange(range, Troops.class); 
        if(troopsInRange.size() > 0){
            Troops closest = troopsInRange.get(0);
            for(Troops t : troopsInRange){
                if(getDistanceFromTower(t) > getDistanceFromTower(closest)){
                    closest = t;
                }
            }
            target = closest;
        }
        if(target != null) shootArrowAtTarget();
    }
    
    private void shootArrowAtTarget(){
        
    }
    
    private double getDistanceFromTower(Troops t){
        return Math.sqrt(Math.pow(t.getX() - getX(), 2) + Math.pow(t.getY() - getY(), 2));
    }
}

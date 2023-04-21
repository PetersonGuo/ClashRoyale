import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class King here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class King extends Towers {
    /**
     * Constructor for objects of class King
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public King(boolean ally) {
        super(ally);
        hp = 200;
        range = 150;
        shootingCooldown = 30;
        image = (ally) ? new GreenfootImage("KingTower1.png") : new GreenfootImage("KingTower2.png") ;
        image.scale(60, 60);
        setImage(image);
    }
    
    public void getHit(int dmg) {
        hp -= dmg;
        //tower destroyed
        if (hp <= 0) {
            ((Worlds)getWorld()).nextWorld();
            getWorld().removeObject(this);
        }
    }
}

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
        
        hpBar = new SuperStatBar(hp, hp, this, 50, 10, (ally)? 25 : -25, Color.GREEN, Color.RED, true);
    }
    
    public void getHit(int dmg) {
        hp -= dmg;
        hpBar.update(hp);
        //tower destroyed
        if (hp <= 0) {
            if(ally)
                numAllyTowers--;
            else
                numEnemyTowers--;
            ((MainWorld)getWorld()).updateScore(numAllyTowers, numEnemyTowers);
            ((Worlds)getWorld()).nextWorld();
            getWorld().removeObject(this);
        }
    }
}

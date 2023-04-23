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
    public King(boolean ally, double hpMultiplyer) {
        super(ally, hpMultiplyer);
        hp = 200;
        range = 150;
        shootingCooldown = 30;
        
        image = (ally) ? new GreenfootImage("KingTower1.png") : new GreenfootImage("KingTower2.png") ;
        image.scale(60, 60);
        setImage(image);
        
        hpBar = new SuperStatBar((int)hp, (int)hp, this, 50, 10, (ally)? 25 : -25, Color.GREEN, Color.RED, true);
    }
    
    public void act(){
        if(hp < 200 || (numAllyTowers < 3 && ally) || (numEnemyTowers < 3 && !ally)){
            super.act();
        }
    }    
    
    public void getHit(double dmg) {
        hp -= dmg;
        hpBar.update((int)hp);
        //tower destroyed
        if (hp <= 0) {
            alive = false;
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

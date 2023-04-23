import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class King here.
 * 
 * @author Kelby To 
 * @version 1.0
 */
public class King extends Towers {
    /**
     * Constructor for objects of class King
     * 
     * @param ally Whether the tower is on the left or right side
     * @param hpMultiplyer The hp multiplyer of the tower
     * @param dmgMultiplyer The damage multiplyer of the tower
     */
    public King(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        hp = 200;
        range = 150;
        damage = 8;
        shootingCooldown = 30;
        
        image = (ally) ? new GreenfootImage("KingTower1.png") : new GreenfootImage("KingTower2.png") ;
        image.scale(60, 60);
        setImage(image);
        
        hpBar = new SuperStatBar((int)hp, (int)hp, this, 50, 10, (ally)? 25 : -25, Color.GREEN, Color.RED, true);
    }
    
    /**
     * Act - do whatever the King wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(hp < 200 || (numAllyTowers < 3 && ally) || (numEnemyTowers < 3 && !ally)){
            super.act();
        }
    }    
    
    /**
     * Take damage from a projectile or troop
     * @param dmg The amount of damage taken
     */
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

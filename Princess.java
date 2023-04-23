import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Normal here.
 * 
 * @author Kelby To 
 * @version 1.0
 */
public class Princess extends Towers {
    /**
     * Constructor for objects of class Princess
     * 
     * @param ally Whether the tower is on the left or right side
     * @param hpMultiplyer The health multiplyer of the tower
     * @param dmgMultiplyer The damage multiplyer of the tower
     */
    public Princess(boolean ally, double hpMultiplyer, double dmgMultiplyer) {
        super(ally, hpMultiplyer, dmgMultiplyer);
        hp = 100;
        range = 120;
        damage = 6;
        shootingCooldown = 45;
        image = (ally) ? new GreenfootImage("PrincessTower1.png") : new GreenfootImage("PrincessTower2.png") ;
        image.scale(55,55);
        setImage(image);
        destroyedSound = new GreenfootSound("TowerDestroyed.mp3");
        
        hpBar = new SuperStatBar((int)hp, (int)hp, this, 45, 10, (ally)? 25 : -25, Color.GREEN, Color.RED, false);        
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
            destroyedSound.play();
            if (ally)
                numAllyTowers--;
            else
                numEnemyTowers--;
            ((MainWorld)getWorld()).updateScore(3-numEnemyTowers, 3-numAllyTowers);
            getWorld().removeObject(this);
        }
    }
}

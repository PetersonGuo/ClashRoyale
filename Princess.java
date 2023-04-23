import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the code for the Princess class, which is a subclass of the Towers class.
 * It represents a type of tower in a game. The Princess class sets the initial 
 * for the tower's health, range, damage, and shooting cooldown. It also sets the
 * tower's image and creates a health bar for the tower. The getHit method is 
 * called when the tower is hit by a projectile or troop, and it reduces the
 * tower's health. If the tower's health reaches 0 or less, the tower is removed
 * from the game and its numAllyTowers or numEnemyTowers variable is decremented.
 * The score of the game is also updated, and a sound effect is played.
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

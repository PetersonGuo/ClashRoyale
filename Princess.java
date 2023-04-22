import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Normal here.
 * 
 * @author Kelby To 
 * @version (a version number or a date)
 */
public class Princess extends Towers {
    /**
     * Constructor for objects of class Princess
     * 
     * @param ally Whether the tower is on the left or right side
     */
    public Princess(boolean ally) {
        super(ally);
        hp = 100;
        range = 120;
        shootingCooldown = 45;
        image = (ally) ? new GreenfootImage("PrincessTower1.png") : new GreenfootImage("PrincessTower2.png") ;
        image.scale(55,55);
        setImage(image);
        destroyedSound = new GreenfootSound("TowerDestroyed.mp3");
        
        hpBar = new SuperStatBar(hp, hp, this, 45, 10, (ally)? 25 : -25, Color.GREEN, Color.RED, false);        
    }
    
    public void getHit(int dmg) {
        hp -= dmg;
        hpBar.update(hp);
        //tower destroyed
        if (hp <= 0) {
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

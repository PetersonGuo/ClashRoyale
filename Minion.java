import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minion extends Troops
{
    /**
     * Act - do whatever the Minion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Minion(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 2;
        attackSpeed = 100; //the higher the number the slower the attacks
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 150;
        
        //attack stats
        damage = 1;
        size = 25;
        attackRange = 15 + size; 
        detectionRange = 180;
        attackSound = new GreenfootSound("MinionAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 3;
        air = true;
        
        walkImages = new GreenfootImage[4];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("minion "+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("minion.png");
        attackImages[0].scale(size, size);
        
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    public void act()
    {
        super.act();
        if(spawning){
            spawn();
        } else if(alive) {
            Actor troop = findTarget(Troops.class);
            if(troop != null){
                moveTowardsTarget(troop);
            } else {
                moveTowardsTarget(findTarget(Towers.class));
            }
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    public void addedToWorld(World w){
        w.addObject(healthBar, 0, 0);
    }
    
    public void attack(Actor a){
        if(actCounter % attackSpeed <= attackSpeed / 5){
            animate(attackImages);
            if(a instanceof Troops){
                ((Troops)a).getHit(damage);
            }else if(a instanceof Towers){
                ((Towers)a).getHit(damage);
            } 
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
}

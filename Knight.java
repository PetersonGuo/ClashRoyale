import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Troops
{
    
    public Knight(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 125;
        animationSpeed = 12;
        
        //health stats
        currentHealth = maxHealth = 350;
        
        //attack stats
        damage = 2;
        size = 25;
        attackRange = 15 + size;
        detectionRange = 180;
        attackSound = new GreenfootSound("KnightAttack.mp3");

        //miscellaneous stats
        elixirCost = 3;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("knight walk "+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages[0] = new GreenfootImage("knight attack.png");
        attackImages[0].scale(size, size);
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            //hit
            setImage(attackImages[0]);
            if(a instanceof Troops){
                if(((Troops)a).isAir() != isAir()){
                    ((Troops)a).getHit(damage);
                }
            }else if(a instanceof Towers){
                ((Towers)a).getHit(damage);
            }
        } else { //while not attacking
            setImage(walkImages[0]);
        }        
    }
        
    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(spawning){
            spawn();
        } else if(alive) {
            Actor troop = findTarget(Troops.class);
            if(troop != null){
                moveTowardsTarget(troop);
            }else if(!crossedBridge){
                moveTowardsTarget(findTarget(Bridge.class));
                if(isTouching(Bridge.class)){
                    crossBridge();
                }
            }else{
                moveTowardsTarget(findTarget(Towers.class));
            } die();
        } else {
            getWorld().removeObject(this);
        }
    }
}

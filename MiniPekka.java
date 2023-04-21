import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniPekka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniPekka extends Troops
{
    
    public MiniPekka(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 1.5;
        attackSpeed = 250;
        animationSpeed = 25;
        
        //health stats
        currentHealth = maxHealth = 450;
        
        //attack stats
        damage = 4;
        size = 30;
        attackRange = 15 + size;
        detectionRange = 180;
        attackSound = new GreenfootSound("MiniPekkaAttack.mp3");

        //miscellaneous stats
        elixirCost = 4;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("pekka_walk_"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("pekka_attack.png");
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
     * Act - do whatever the MiniPekka wants to do. This method is called whenever
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
            }
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
}

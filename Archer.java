import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Troops
{
    protected Troops target; 
    int imageNumber;
    
    public Archer(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 100;
        animationSpeed = 10;
        
        //health stats
        currentHealth = maxHealth = 175;
        
        //attack stats
        damage = 1;
        size = 25;
        attackRange = 40 + size;
        detectionRange = 350;
        attackSound = new GreenfootSound("ArcherAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 3;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("walking archer "+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("archer attack.png");
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
            if (troop != null){
                moveTowardsTarget(troop);
            } else if(!crossedBridge){
                moveTowardsTarget(findTarget(Bridge.class));
                if(isTouching(Bridge.class)){
                    crossBridge();
                }
            } else {
                moveTowardsTarget(findTarget(Towers.class));
            }
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    public void attack(Actor a){
        if (actCounter % attackSpeed == 0){
            Troops target = (Troops)findTarget(Troops.class);
            shootArrowAtTarget();
            if (target != null){
                moveTowardsTarget(target);
            } else{
                animate(attackImages);
                if(a instanceof Troops){
                    shootArrowAtTarget();
                    ((Troops)a).getHit(damage);
                }else if(a instanceof Towers){
                    shootArrowAtTarget();
                    ((Towers)a).getHit(damage);
                }
            }
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
    
    private void shootArrowAtTarget() {
        
        getWorld().addObject(new Arrow(target), getX(), getY());
        actCounter = 0;
    }
}

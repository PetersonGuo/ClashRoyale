import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniPekka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniPekka extends Troops
{
    
    public MiniPekka(boolean ally) {
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 80; //the higher the number the slower the attacks
        animationSpeed = 25;
        
        //health stats
        currentHealth = maxHealth = 70;
        
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
        for(int i = 0; i < walkImages.length; i++) {
            walkImages[i] = new GreenfootImage("PekkaWalk"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("PekkaAtk.png");
        attackImages[0].scale(size, size);
        
        setImage(walkImages[0]);
        
        healthBar = new SuperStatBar(maxHealth, currentHealth, this, size, 10, -size / 2, filledColor, missingColor, false);
    }
    
    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (spawning) {
            spawn();
        } else if (alive) {
            Actor troop = findTarget(Troops.class);
            if (troop != null) { //If there is a target
                moveTowardsTarget(troop);
            }else if (!crossedBridge) { //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if (isTouching(Bridge.class)) {
                    crossBridge();
                }
            }else{ //If there is no target
                moveTowardsTarget(findTarget(Towers.class));
            } 
            die();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    public void addedToWorld(World w) {
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * Attack method
     * 
     * @param a the actor to attack
     */
    public void attack(Actor a) {
        animate(attackImages);
        if (actCounter % attackSpeed == 0) { // If the attack counter is reached
            if (a instanceof Troops) { //If target is a troop
                if (((Troops)a).isAir() != true) { //Check if air
                    ((Troops)a).getHit(damage);
                    System.out.println("yup");
                }
            }else if (a instanceof Towers) { //If target is a tower
                ((Towers)a).getHit(damage);
            }
        } else if (actCounter % attackSpeed <= 10){
            setImage(walkImages[0]);
        }   
    }
}


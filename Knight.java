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
        attackSpeed = 125; //the higher the number the slower the attacks
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
            walkImages[i] = new GreenfootImage("KnightWalk"+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages[0] = new GreenfootImage("KnightAtk.png");
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
        if(spawning){
            spawn();
        } else if(alive) {
            Actor troop = findTarget(Troops.class);
            if(troop != null){ //If there is a target
                moveTowardsTarget(troop);
            }else if(!crossedBridge){ //If have not crossed bridge
                moveTowardsTarget(findTarget(Bridge.class));
                if(isTouching(Bridge.class)){
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
    
    public void addedToWorld(World w){
        w.addObject(healthBar, 0, 0);
    }
    
    /**
     * Attack method
     * 
     * @param a the actor to attack
     */
    public void attack(Actor a){
        if(actCounter % attackSpeed <= attackSpeed / 5){ // If the attack counter is reached
            animate(attackImages);
            if(a instanceof Troops){ //If target is a troop
                if(((Troops)a).isAir() != isAir()){ //Check if air
                    ((Troops)a).getHit(damage);
                }
            }else if(a instanceof Towers){ //If target is a tower
                ((Towers)a).getHit(damage);
            }
        } else { //while not attacking
            setImage(walkImages[0]);
        }        
    }
}

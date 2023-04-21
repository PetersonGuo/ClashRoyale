import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Troops {
    /**
     * Constructor for objects of class Knight
     * 
     * @param ally whether the troop is on the player's side or not
     */
    public Knight(boolean ally) {
        super(ally);
        
        maxSpeed = 8;
        attackSpeed = 60;
        animationSpeed = 15;
        
        currentHealth = 8;
        damage = 3;
        attackRange = 15;
        detectionRange = 60;

        elixirCost = 3;
        air = true;
        
        walkImages = new GreenfootImage[3];
        walkImages[0] = new GreenfootImage("KnightWalk0.png");
        walkImages[1] = new GreenfootImage("KnightWalk1.png");
        walkImages[2] = new GreenfootImage("KnightWalk2.png");
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("KnightAtk.png");
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("KnightWalk "+ i + ".png");
            walkImages[i].scale(walkImages[i].getWidth()/2, walkImages[i].getHeight()/2);
        }
        attackImages[0].scale(attackImages[0].getWidth()/2, attackImages[0].getHeight()/2);
        setImage(walkImages[0]);
    }
    
    /**
     * Attack method
     * 
     * @param a the actor to attack
     */
    public void attack(Actor a){
        if(actCounter % attackSpeed == 0){
            //hit
            setImage(attackImages[0]);
            if(a instanceof Troops){
                ((Troops)a).getHit(damage);
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
    public void act() {
        actCounter++;
        if(spawning)
            spawn();
        Actor troop = findTarget(Troops.class);
        if (troop != null)
            moveTowardsTarget(troop);
        else if(!crossedBridge)
            moveTowardsTarget(findTarget(Bridge.class));
        else
            moveTowardsTarget(findTarget(Towers.class));
    }
}

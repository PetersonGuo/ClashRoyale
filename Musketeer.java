import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musketeer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Musketeer extends Troops
{
    protected Troops target;
    
    
    public Musketeer(boolean ally){
        super(ally);
        
        //speed stats
        maxSpeed = 1;
        attackSpeed = 150;
        animationSpeed = 15;
        
        //health stats
        currentHealth = maxHealth = 300;
        
        //attack stats
        damage = 3;
        size = 25;
        attackRange = 60 + size;
        detectionRange = 400;
        attackSound = new GreenfootSound("MusketeerAttack.mp3");
        
        //miscellaneous stats
        elixirCost = 4;
        air = false;
        
        walkImages = new GreenfootImage[3];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("musketeer "+ i + ".png");
            walkImages[i].scale(size, size);
        }
        
        attackImages = new GreenfootImage[1];
        attackImages[0] = new GreenfootImage("musketeer attack.png");
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
            }
            else if(!crossedBridge){
                moveTowardsTarget(findTarget(Bridge.class));
                if(isTouching(Bridge.class)){
                    crossBridge();
                }
            }
            else{
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
            if (target != null){
                moveTowardsTarget(target);
            }else{
                animate(attackImages);
                if(a instanceof Troops){
                    shootPelletAtTarget();
                    ((Troops)a).getHit(damage);
                }else if(a instanceof Towers){
                    shootPelletAtTarget();
                    ((Towers)a).getHit(damage);
                }
            }
        } else { //while not attacking
            setImage(walkImages[0]);
        }
    }
    
    private void shootPelletAtTarget() {
        getWorld().addObject(new Pellet(target), getX(), getY());
        actCounter = 0;
    }
}

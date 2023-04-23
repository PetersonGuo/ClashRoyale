import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor is created when troop dies
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class Dead extends Actor {
    private GreenfootImage image; // The image of the dead
    private int size, transparency; // The size and transparency of the dead
    /**
     * Constructor for objects of class Dead
     * @param size The size of the image 
     */
    public Dead(int size) {
        image = new GreenfootImage("dead.png");
        this.size = size;
        transparency = 200;
        image.setTransparency(transparency);
        image.scale(size / 2, size / 2);
        setImage(image);
    }
    
    /**
     * Act - do whatever the dead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        transparency-=5;
        image.setTransparency(transparency);
        if(transparency <= 0){
            getWorld().removeObject(this);
        }
    }
}

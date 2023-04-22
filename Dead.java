import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class dead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dead extends Actor
{
    private GreenfootImage image;
    private int size, transparency;
    /**
     * Act - do whatever the dead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Dead(int size){
        image = new GreenfootImage("dead.png");
        this.size = size;
        transparency = 200;
        image.setTransparency(transparency);
        image.scale(size / 2, size / 2);
        setImage(image);
    }
    
    public void act()
    {
        transparency-=5;
        image.setTransparency(transparency);
        if(transparency <= 0){
            getWorld().removeObject(this);
        }
    }
}

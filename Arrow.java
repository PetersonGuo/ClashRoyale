import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Arrow extends Actor {
    private boolean left;
    private String selector;
    public Arrow(boolean left, String selector) {
        this.left = left;
        this.selector = selector;
        getImage().scale(getImage().getWidth() * 25 / getImage().getHeight(), 25);
        if (left)
            getImage().rotate(-90);
        else
            getImage().rotate(90);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this))
            ((ChooseScreen) getWorld()).setValue(this);
    }
    
    public String getSelector() {
        return selector;
    }
    
    public boolean isLeft() {
        return left;
    }
}

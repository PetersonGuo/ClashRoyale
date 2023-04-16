import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chevron here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Chevron extends Actor {
    private boolean left;
    private String selector;
    private Text text;
    public Chevron(boolean left, String selector, int size) {this(left, selector, size, null);}
    public Chevron(boolean left, String selector, int size, Text text) {
        this.left = left;
        this.selector = selector;
        this.text = text;
        getImage().scale(getImage().getWidth() * size / getImage().getHeight(), size);
        if (left)
            getImage().rotate(-90);
        else
            getImage().rotate(90);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this) && text != null)
            ((ChooseScreen) getWorld()).setValue(this);
    }
    
    public String getSelector() {
        return selector;
    }
    
    public boolean isLeft() {
        return left;
    }
    
    public Text getText() {
        return text;
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chevron here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Chevron extends Actor {
    private boolean left; // Is the chevron on the left
    private String selector; // The selector
    private Text text; // The text
    /**
     * Constructor for objects of class Chevron
     * 
     * @param left Whether the chevron is on the left or right
     * @param selector The selector
     * @param size The size of the chevron
     */
    public Chevron(boolean left, String selector, int size) {this(left, selector, size, null);} // Overloaded constructor
    /**
     * Constructor for objects of class Chevron
     * 
     * @param left Whether the chevron is on the left or right
     * @param selector The selector
     * @param size The size of the chevron
     * @param text The text
     */
    public Chevron(boolean left, String selector, int size, Text text) { // Constructor
        this.left = left;
        this.selector = selector;
        this.text = text;
        getImage().scale(getImage().getWidth() * size / getImage().getHeight(), size);
        if (left)
            getImage().rotate(-90);
        else
            getImage().rotate(90);
    }
    
    /**
     * Act - do whatever the Chevron wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this) && text != null) // If the mouse is clicked on the chevron
            ((ChooseScreen) getWorld()).setValue(this);
    }
    
    // Getters and setters
    public String getSelector() {
        return selector;
    }
    
    public boolean isLeft() { // Is the chevron on the left
        return left;
    }
    
    public Text getText() { // Get the text
        return text;
    }
}
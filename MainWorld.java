import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
/**
 * Write a description of class MainWorld here.
 * 
 * Sources:
 * Arrow https://www.pngimages.pics/images/quotes/english/general/transparent-background-arrow-head-png-52650-242281.png
 * 
 * @author Peterson Guo 
 * @version 1.0
 */
public class MainWorld extends Worlds {
    private int elixir, elixirTimer;
    /**
     * Constructor for objects of class MainWorld.
     */
    public MainWorld(Map<String, Integer> stats) {
        for (int i = 0; i < 4; i++) {
            Card c = new Card(1);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING, FINAL.WORLD_HEIGHT * 7 / 8);
        }
        elixir = 5;
        elixirTimer = 0;
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new EndScreen());
    }
    
    public void act() {
        elixir += elixirTimer % 60;
        elixirTimer %= 60;
    }
}

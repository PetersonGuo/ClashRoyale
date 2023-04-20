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
    /**
     * Constructor for objects of class MainWorld.
     */
    public MainWorld(Map<String, Integer> stats) {
        for (int i = 0; i < 8; i++) {
            Card c = new Card(1, 70, 90, true);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * (i % 4) + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, i > 3 ? 90 : 665);
        }
        
        addObject(new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time")), 250, 730);
        addObject(new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time")), 250, 20);
        addObject(new Card(40, 53), 40, 720);
        addObject(new Card(40, 53), 40, 35);
        addObject(new Text("Next:", Color.WHITE, 18), 40, 680);
        addObject(new Text("Next:", Color.WHITE, 18), 40, 70);
        addObject(new Text(0, Color.BLUE, 30), 401, 395);
        addObject(new Text(0, Color.RED, 30), 401, 237);
        
        addObject(new Princess(true), 106, 525);
        addObject(new King(true), 211, 575);
        addObject(new Princess(true), 317, 525);
        
        addObject(new Princess(false), 106, 225);
        addObject(new King(false), 211, 175);
        addObject(new Princess(false), 317, 225);
        
        addObject(new Bridge(), 106, 375);
        addObject(new Bridge(), 317, 375);
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new EndScreen());
    }
}

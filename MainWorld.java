import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
/**
 * Write a description of class MainWorld here.
 * 
 * Sources:
 * Chevron https://www.pngimages.pics/images/quotes/english/general/transparent-background-arrow-head-png-52650-242281.png
 * 
 * @author Peterson Guo 
 * @version 1.0
 */
public class MainWorld extends Worlds {
    private Card enemyNext, allyNext; // The next cards for the enemy and ally
    private ElixirBar enemyElixir, allyElixir; // The elixir bars
    /**
     * Constructor for objects of class MainWorld.
     * 
     * @param stats The stats of the game
     */
    public MainWorld(Map<String, Integer> stats) {
        for (int i = 0; i < 8; i++) {
            Card c = new Card(1, 70, 90, true, i > 3 ? true : false, (int) Math.random() * FINAL.NUM_OF_TROOPS);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * (i % 4) + c.getWidth() / 2 + FINAL.CARD_SPACING + 90,
                    i > 3 ? 90 : 665);
        }
        enemyElixir = new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time"));
        allyElixir = new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time"));
        addObject(allyElixir, 250, 730);
        addObject(enemyElixir, 250, 20);
        addObject(new Text("Next:", Color.WHITE, 18), 40, 680);
        addObject(new Text("Next:", Color.WHITE, 18), 40, 70);
        addObject(new Text(0, Color.BLUE, 30), 401, 395);
        addObject(new Text(0, Color.RED, 30), 401, 237);
        nextCard(true);
        nextCard(false);

        addObject(new Princess(true), 106, 525);
        addObject(new King(true), 211, 575);
        addObject(new Princess(true), 317, 525);

        addObject(new Princess(false), 106, 225);
        addObject(new King(false), 211, 175);
        addObject(new Princess(false), 317, 225);

        addObject(new Bridge(), 106, 375);
        addObject(new Bridge(), 317, 375);
    }
    
    /**
     * Get the next card
     * 
     * @param enemy Is the card for the enemy
     * @return The next card
     */
    public Card nextCard(boolean enemy) {
        Card c;
        if (enemy) {
            c = enemyNext;
            removeObject(enemyNext);
            enemyNext = new Card(40, 53, true, (int) Math.random() * FINAL.NUM_OF_TROOPS);
            addObject(enemyNext, 40, 35);
        } else {
            c = allyNext;
            removeObject(allyNext);
            allyNext = new Card(40, 53, false, (int) Math.random() * FINAL.NUM_OF_TROOPS);
            addObject(allyNext, 40, 720);
        }
        return c;
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public void nextWorld() {
        Greenfoot.setWorld(new EndScreen());
    }
    
    /**
     * Get the elixir bar
     * 
     * @param enemy Is the elixir bar for the enemy
     * @return The elixir bar
     */
    public ElixirBar getElixir(boolean enemy) {
        return enemy ? enemyElixir : allyElixir;
    }
}

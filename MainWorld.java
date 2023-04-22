import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.*;
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
    private Queue<Integer> enemyCards, allyCards; // Card orders
    /**
     * Constructor for objects of class MainWorld.
     * 
     * @param stats The stats of the game
     */
    public MainWorld(Map<String, Integer> stats) {
        enemyElixir = new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time"));
        allyElixir = new ElixirBar(stats.get("Start Elixir"), stats.get("Max Elixir"), stats.get("Elixir Time"));
        addObject(allyElixir, 250, 730);
        addObject(enemyElixir, 250, 20);
        
        addObject(new Text("Next:", Color.BLACK, 18), 40, 680);
        addObject(new Text("Next:", Color.BLACK, 18), 40, 70);
        
        addObject(new Text(0, Color.BLUE, 30), 409, 453);
        addObject(new Text(0, Color.RED, 30), 409, 308);
        
        //towers
        addObject(new Princess(true), 93, 512);
        addObject(new King(true), 213, 586);
        addObject(new Princess(true), 333, 512);

        addObject(new Princess(false), 93, 248);
        addObject(new King(false), 213, 177);
        addObject(new Princess(false), 333, 248);

        //bridge
        addObject(new Bridge(), 93, 381);
        addObject(new Bridge(), 333, 381);
        
        // timer
        addObject(new Timer(), FINAL.WORLD_WIDTH * 14 / 15, FINAL.WORLD_HEIGHT / 2);
        
        // Randomize card order
        List<Integer> enemy = new ArrayList<>() {{for (int i = 0; i < FINAL.NUM_OF_TROOPS; i++) add(i);}},
        ally = new ArrayList<>() {{for (int i = 0; i < FINAL.NUM_OF_TROOPS; i++) add(i);}};
        Collections.shuffle(enemy);
        Collections.shuffle(ally);
        
        enemyCards = new LinkedList<>(enemy);
        allyCards = new LinkedList<>(ally);
        
        for (int i = 0; i < 4; i++) {
            int next = enemyCards.poll();
            Card c = new Card(1, 75, 90, true, true, next);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, 90);
            enemyCards.add(next);
        }
        
        for (int i = 0; i < 4; i++) {
            int next = allyCards.poll();
            Card c = new Card(1, 75, 90, true,  false, next);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, 665);
            allyCards.add(next);
        }
        
        nextCard(true);
        nextCard(false);
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
            int next = enemyCards.poll();
            enemyNext = new Card(42, 53, enemy, next);
            enemyCards.add(next);
            addObject(enemyNext, 40, 35);
        } else {
            c = allyNext;
            removeObject(allyNext);
            int next = allyCards.poll();
            allyNext = new Card(42, 53, enemy, next);
            allyCards.add(next);
            addObject(allyNext, 40, 720);
        }
        return c;
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public void nextWorld() {
        // To Do: Set Timer, keep track of crowns
        Greenfoot.setWorld(new EndScreen(new int[]{0,0}));
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

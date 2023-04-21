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
        addObject(new Text("Next:", Color.WHITE, 18), 40, 680);
        addObject(new Text("Next:", Color.WHITE, 18), 40, 70);
        addObject(new Text(0, Color.BLUE, 30), 401, 395);
        addObject(new Text(0, Color.RED, 30), 401, 237);
        addObject(new Princess(true), 100, 520);
        addObject(new King(true), FINAL.WORLD_WIDTH / 2, 575);
        addObject(new Princess(true), 320, 520);

        addObject(new Princess(false), 100, 225);
        addObject(new King(false), 210, 175);
        addObject(new Princess(false), 320, 225);

        addObject(new Bridge(), 100, (520 + 225) / 2);
        addObject(new Bridge(), 320, (520 + 225) / 2);
        
        // Randomize card order
        Integer[] nums = new Integer[FINAL.NUM_OF_TROOPS];
        for (int i = 0; i < FINAL.NUM_OF_TROOPS; i++) nums[i] = i;
        List<Integer> enemy = Arrays.asList(nums);
        List<Integer> ally = Arrays.asList(nums);
        Collections.shuffle(enemy);
        Collections.shuffle(ally);
        
        enemyCards = new LinkedList<>(enemy);
        allyCards = new LinkedList<>(ally);
        
        for (int i = 0; i < 4; i++) {
            int next = enemyCards.poll();
            Card c = new Card(1, 70, 90, true, true, next);
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, 90);
            enemyCards.add(next);
        }
        
        for (int i = 0; i < 4; i++) {
            int next = allyCards.poll();
            Card c = new Card(1, 70, 90, true,  false, next);
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
            enemyNext = new Card(40, 53, enemy, next);
            enemyCards.add(next);
            addObject(enemyNext, 40, 35);
        } else {
            c = allyNext;
            removeObject(allyNext);
            int next = allyCards.poll();
            allyNext = new Card(40, 53, enemy, next);
            allyCards.add(next);
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

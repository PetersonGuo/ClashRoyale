import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.*;
/**
 * Main world for the game where all the action happens. This is a simulation of the 
 * game Clash Royale and spawns troops into the world based on the card that is selected.
 * Cards are chosen at random and can only deploy troops if there is enough elixir 
 * available to the ally/enemy. Troops will fight each other until either one teams king
 * tower dies or the timer ends. The winner is decided based on the number of towers destroyed
 * or if the king tower is destroyed. This simulation was created by the following people:
 * Isaac Chan, Peterson Guo, Kelby To, Kevin Luo, Jett Miyasaki. Credit goes to Mr.Cohen for
 * providing the following classes: SuperStatBar, Button
 * 
 * Sources:
 * Chevron https://www.pngimages.pics/images/quotes/english/general/transparent-background-arrow-head-png-52650-242281.png
 * Clash Royale Sounds https://jayuzumi.com/clash-royale-sounds
 * Visuals (Background and Actors, except cards) Jett Miyasaki
 * Cards Clash Royale Game
 * 
 * @author Peterson Guo 
 * @version 1.0
 */
public class MainWorld extends Worlds {
    private Card enemyNext, allyNext; // The next cards for the enemy and ally
    private ElixirBar enemyElixir, allyElixir; // The elixir bars
    private Queue<Integer> enemyCards, allyCards; // Card orders
    private Timer timer; // The timer
    private Text allyScoreText, enemyScoreText; // The score texts
    private King enemyKingTower, allyKingTower; // The king towers
    static Map<String, Double> stats; // The stats of the game
    private Sound background;
    /**
     * Constructor for objects of class MainWorld.
     * 
     * @param stats The stats of the game
     */
    public MainWorld(Map<String, Double> stats) {
        MainWorld.stats = stats;
        enemyElixir = new ElixirBar(stats.get("Start Elixir").intValue(), stats.get("Max Elixir").intValue(), stats.get("Elixir Speed").intValue());
        allyElixir = new ElixirBar(stats.get("Start Elixir").intValue(), stats.get("Max Elixir").intValue(), stats.get("Elixir Speed").intValue());
        addObject(allyElixir, 250, 730);
        addObject(enemyElixir, 250, 20);
        
        addObject(new Text("Next:", Color.BLACK, 18), 40, 680);
        addObject(new Text("Next:", Color.BLACK, 18), 40, 70);
        
        allyScoreText = new Text(0, Color.BLUE, 30);
        enemyScoreText = new Text(0, Color.RED, 30);
        addObject(allyScoreText, 409, 453);
        addObject(enemyScoreText, 409, 308);
        
        Towers.resetTowers();
        //towers
        allyKingTower = new King(true, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue());
        addObject(new Princess(true, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue()), 93, 512);
        addObject(allyKingTower, 213, 586);
        addObject(new Princess(true, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue()), 333, 512);

        enemyKingTower = new King(false, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue());
        addObject(new Princess(false, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue()), 93, 248);
        addObject(enemyKingTower, 213, 177);
        addObject(new Princess(false, stats.get("Tower Health Multiplyer").doubleValue(), stats.get("Tower Damage Multiplyer").doubleValue()), 333, 248);

        //bridge
        addObject(new Bridge(), 93, 381);
        addObject(new Bridge(), 333, 381);
        
        //timer
        timer = new Timer();
        addObject(timer, 40, 105);
        
        // Randomize card order
        List<Integer> enemy = new ArrayList<>() {{for (int i = 0; i < FINAL.NUM_OF_TROOPS; i++) add(i);}},
        ally = new ArrayList<>() {{for (int i = 0; i < FINAL.NUM_OF_TROOPS; i++) add(i);}};
        Collections.shuffle(enemy);
        Collections.shuffle(ally);
        
        enemyCards = new LinkedList<>(enemy);
        allyCards = new LinkedList<>(ally);
        
        for (int i = 0; i < 4; i++) {
            Card c = new Card(1, 75, 90, true, true, enemyCards.poll());
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, 90);
        }
        
        for (int i = 0; i < 4; i++) {
            Card c = new Card(1, 75, 90, true,  false, allyCards.poll());
            addObject(c, (FINAL.CARD_SPACING + c.getWidth()) * i + c.getWidth() / 2 + FINAL.CARD_SPACING + 90, 665);
        }
        
        nextCard(true);
        nextCard(false);
        
        background = new Sound("Background.mp3");
        background.loop();
    }
    
    public void stopped(){
        background.stop();
    }
    
    /**
     * Act - do whatever the MainWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        background.play();
        // Get all 8 ally and enemy cards a separate them
        List<Card> allyCards = new ArrayList<>(), enemyCards = new ArrayList<>();
        for (Card c : getObjects(Card.class))
            if (c.isPlayable())
                if (c.isAlly()) allyCards.add(c);
                else enemyCards.add(c);
                
        if ((int) (Math.random() * 60) == 0) // 1/60 percent chance of spawning or 1 per second
            allyCards.get((int) (Math.random() * allyCards.size())).selectCard((int) (Math.random() * 60) + 20);
        if ((int) (Math.random() * 60) == 0)
            enemyCards.get((int) (Math.random() * enemyCards.size())).selectCard((int) (Math.random() * 60) + 20);
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
            enemyNext = new Card(42, 53, enemy, enemyCards.poll());
            addObject(enemyNext, 40, 35);
        } else {
            c = allyNext;
            removeObject(allyNext);
            allyNext = new Card(42, 53, enemy, allyCards.poll());
            addObject(allyNext, 40, 720);
        }
        return c;
    }
    
    /**
     * Add a card to the queue
     * 
     * @param type The type of card
     * @param ally Is the card for the ally
     */
    public void addCardToQueue(int type, boolean ally) {
        if (ally)
            allyCards.add(type);
        else
            enemyCards.add(type);
    }
    
    /**
     * Update the score / crowns
     * @param allyScore The ally score
     * @param enemyScore The enemy score
     */
    public void updateScore(int allyScore, int enemyScore){
        allyScoreText.updateText(allyScore);
        enemyScoreText.updateText(enemyScore);
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public void nextWorld() {
        // To Do: Set Timer, keep track of crowns
        Greenfoot.setWorld(new EndScreen(Towers.getCrowns()));
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
    
    /**
     * Get the king tower
     * @param enemy Ally or enemy tower to return
     * @return The king tower corresponding to the parameter
     */
    public King getKingTower(boolean enemy) {
        return enemy ? enemyKingTower : allyKingTower;
    }
}
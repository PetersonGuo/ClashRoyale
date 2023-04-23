import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * ClashRoyale card deck class
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Card extends Actor {
    private GreenfootImage img; // Image for the card
    private int cost, x, xSpeed, y, ySpeed, type, width, height, time; // Cost of the card, x and y coordinates, and type of card 
    private boolean playable, playing, mouseIsDown, selected, enemy; // Is the card playable, is the mouse down, is the card clicked, and is the card an enemy card
    private Actor target;
    /**
     * Constructor for objects of class Card
     * 
     * @param width The width of the card
     * @param height The height of the card
     * @param enemy Whether the card is an enemy card
     * @param type The type of card
     */
    public Card(int width, int height, boolean enemy, int type) {this(0, width, height, false, enemy, type);} // Constructor for a card with no cost
    /**
     * Constructor for objects of class Card
     * 
     * @param cost The cost of the card
     * @param width The width of the card
     * @param height The height of the card
     * @param playable Whether the card is playable
     * @param enemy Whether the card is an enemy card
     * @param type The type of card
     */
    public Card(int cost, int width, int height, boolean playable, boolean enemy, int type) { // Constructor for a card with a cost
        final String[] imgNames = {"ArrowsCard.png", "FireballCard.png", "ArchersCard.png", "GiantCard.png", "KnightCard.png", "MinionsCard.png", "MusketeerCard.png", "MiniPekkaCard.png"};
        img = new GreenfootImage(imgNames[type]);
        img.scale(width, height);
        setImage(img);
        this.cost = cost;
        this.playable = playable;
        this.enemy = enemy;
        this.type = type;
        this.width = width;
        this.height = height;
        playing = false;
        selected = false;
    }
    
    /**
     * Added to world method
     * @param w the world the card is added to
     */
    public void addedToWorld(World w) {
        x = getX();
        y = getY();
    }
    
    /**
     * Check if the card can be played
     * @param currElixir the current elixir
     * @return if the card can be played
     */
    public boolean canPlay(int currElixir) {
        return cost <= currElixir;
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Math.abs(y - getY()) >= img.getHeight())
            img.scale(42, 53);
        else
            img.scale(width, height);
        setImage(img);
        
        if (selected) { // Select Card
            time --;
            if (time <= 0) {
                int randTime = ((int) (Math.random() * 60)) + 30;
                if (type >= 2) 
                    playCard(FINAL.WORLD_WIDTH / 2 + ((int) (Math.random() * 2) == 0 ? -FINAL.WORLD_WIDTH / 3 : FINAL.WORLD_WIDTH / 3), enemy ? 200 : FINAL.WORLD_HEIGHT - 200, randTime);
                else {
                    target = selectTarget();
                    playCard(target.getX(), target.getY(), randTime);
                }
            }
        } else if (playing) { // Play Card
            time--;
            setLocation(getX() + xSpeed, getY() + ySpeed);
            if (time <= 0) {
                if (((MainWorld)getWorld()).getElixir(enemy).useElixir(FINAL.ELIXIR_COST[type])) {
                    playing = false;
                    Card c = ((MainWorld)getWorld()).nextCard(enemy);
                    spawnCard();
                    getWorld().addObject(new Card(1, getWidth(), getHeight(), true, enemy, c.getType()), x, y);
                    ((MainWorld)getWorld()).addCardToQueue(type, !enemy);
                    getWorld().removeObject(this);
                } else {
                    playing = false;
                    setLocation(x, y);
                }
            }
        }
    }
    
    /**
     * Spawn a card
     */
    private void spawnCard() { // Spawn a character card
        King tower = ((MainWorld) getWorld()).getKingTower(enemy);
        if (type == 0) // Spawn an arrow card
            getWorld().addObject(new Arrows(!enemy, target), tower.getX(), tower.getY());
        else if (type == 1) // Spawn a fireball card
            getWorld().addObject(new Fireball(!enemy, target), tower.getX(), tower.getY());
        else if (type == 2) // Spawn an archer card
            getWorld().addObject(new Archer(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
        else if (type == 3) // Spawn a giant card
            getWorld().addObject(new Giant(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
        else if (type == 4) // Spawn a knight card
            getWorld().addObject(new Knight(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
        else if (type == 5) // Spawn a minion card
            getWorld().addObject(new Minion(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
        else if (type == 6) // Spawn a musketeer card
            getWorld().addObject(new Musketeer(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
        else if (type == 7)
            getWorld().addObject(new MiniPekka(enemy, MainWorld.stats.get("Tower Health Multiplyer"), MainWorld.stats.get("Tower Damage Multiplyer")), getX(), getY());
    }
    
    /**
     * Select card method which indicates that the card is selected and will be played soon
     * @param time the time it takes to play the card
     */
    public void selectCard(int time) {
        for (Card c : getWorld().getObjects(Card.class)) // Set all other cards to not selected
            c.setSelected(false);
        selected = true;
        setLocation(x, enemy ? y + 5 : y - 5);
        this.time = time;
    }

    /**
     * Play the card by dragging it to the target
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     * @param time the time it takes to play the card
     */
    private void playCard(int x, int y, int time) {
        selected = false;
        playing = true;
        xSpeed = (x - getX()) / time;
        ySpeed = (y - getY()) / time;
        this.time = time;
    }
    
    /**
     * Compare the distance between the king tower and the troops
     */
    class Comp implements Comparator<Troops> {
        private King tower;
        /**
         * Constructor for the comparator
         * @param t the king tower
         */
        public Comp(King t) {
            tower = t;
        }
        
        /**
         * Compare the distance between the king tower and the troops
         * @param a the first troop
         * @param b the second troop
         * @return the difference between the distance between the king tower and the troops
         */
        public int compare(Troops a, Troops b) {
            return (int)Math.sqrt(Math.pow(tower.getX() - b.getX(), 2) + Math.pow(tower.getY() - b.getY(), 2)) - (int)Math.sqrt(Math.pow(b.getX() - tower.getX(), 2) + Math.pow(b.getY() - tower.getY(), 2));
        }
    }
    
    /**
     * Select the target to attack
     * @return the target to attack
     */
    private Actor selectTarget() {
        King tower = ((MainWorld)getWorld()).getKingTower(!enemy);
        List<Troops> targets = new ArrayList<>();
        for (Troops t : getWorld().getObjects(Troops.class))
            if (t.isAlly() ^ !enemy)
                targets.add(t);
        Collections.sort(targets, new Comp(tower));
        return targets.size() > 0 ? targets.get(0) : towerTarget();
    }
    
    /**
     * Get the tower with the lowest hp to target
     * @return the tower with the lowest hp
     */
    private Towers towerTarget() {
        List<Towers> towers = new ArrayList<>();
        for (Towers t : getWorld().getObjects(Towers.class))
            if (t.isAlly() ^ !enemy)
                towers.add(t);
        Towers lowest = towers.get(0);
        for (Towers t: towers)  //check for the lowest hp tower to target
            lowest = lowest.getHp() > t.getHp() ? t : lowest;
        return lowest;
    }
    
    /**
     * Get the width of the card
     * @return The width of the card
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the height of the card
     * @return The height of the card
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Set the card to clicked
     * @param clicked Whether the card is clicked
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * Get whether the card is playable
     * @return Whether the card is playable
     */
    public boolean isPlayable() {
        return playable;
    }
    
    /**
     * Return whether the card is an ally or not
     * @return Whether the card is an ally or not
     */
    public boolean isAlly() {
        return !enemy;
    }
    
    /**
     * Get the type of the card
     * @return The type of the card
     */
    public int getType() {
        return type;
    }
}
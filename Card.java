import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ClashRoyale card deck class
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class Card extends Actor {
    private GreenfootImage img; // Image for the card
    private int cost, x, y, type; // Cost of the card, x and y coordinates, and type of card 
    private boolean playable, mouseIsDown, clicked, enemy; // Is the card playable, is the mouse down, is the card clicked, and is the card an enemy card
    
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
        final String[] imgNames = {"ArrowsCard.png", "FireballCard.png", "ArchersCard.png", "GiantCard.png", "KnightCard.png", "MinionCard.png", "MusketeerCard.png"};
        img = new GreenfootImage(imgNames[type]);
        img.scale(width, height);
        setImage(img);
        this.cost = cost;
        this.playable = playable;
        this.enemy = enemy;
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
        if (playable) { // If the card is playable
            MouseInfo m = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this))   // If the mouse is pressed on the card
                mouseIsDown = true;
            else if (Greenfoot.mouseClicked(this)) { // If the mouse is clicked on the card
                mouseIsDown = false;
                for (Card c : getWorld().getObjects(Card.class)) // Set all other cards to not clicked
                    c.setClicked(false);
                clicked = true;
                if ((!enemy && y - m.getY() >= img.getHeight()) || (enemy && m.getY() - y >= img.getHeight())) { // Play card
                    Card c = ((MainWorld)getWorld()).nextCard(enemy);
                    spawnCard();
                    getWorld().addObject(new Card(1, getWidth(), getHeight(), true, enemy, c.getType()), x, y);
                    getWorld().removeObject(this);
                }
            }
            if (mouseIsDown) // If the mouse is down, move the card to the mouse
                setLocation(m.getX(), m.getY()); 
            else if (Greenfoot.mouseMoved(this) || clicked) // If the mouse is moved over the card or the card is clicked, move the card to the original position
                setLocation(x, enemy ? y + 5 : y - 5);
            else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) // If the mouse is moved off the card, move the card to the original position
                setLocation(x, y);
        }
    }
    
    /**
     * Spawn a card
     */
    private void spawnCard() { // Spawn a character card
        int x = FINAL.WORLD_WIDTH / 2 + FINAL.WORLD_WIDTH / 2 * (Math.random() * 2 == 0 ? 1 : -1), y = enemy ? 100 : FINAL.WORLD_HEIGHT - 100; // Spawn the card at a random location on the enemy side
        switch (type) {
            case 0: // Spawn an arrow card
                getWorld().addObject(new Arrows(enemy), x, y);
                break;
            case 1: // Spawn a fireball card
                getWorld().addObject(new Fireball(enemy), x, y);
                break;
            case 2: // Spawn an archer card
                getWorld().addObject(new Archers(enemy), x, y);
                break;
            case 3: // Spawn a giant card
                getWorld().addObject(new Giant(enemy), x, y);
                break;
            case 4: // Spawn a knight card
                getWorld().addObject(new Knight(enemy), x, y);
                break;
            case 5: // Spawn a minion card
                getWorld().addObject(new Minion(enemy), x, y);
                break;
            case 6: // Spawn a musketeer card
                getWorld().addObject(new Musketeer(enemy), x, y);
                break;
        }
    }
    
    /**
     * Get the width of the card
     * @return The width of the card
     */
    public int getWidth() {
        return img.getWidth();
    }
    
    /**
     * Get the height of the card
     * @return The height of the card
     */
    public int getHeight() {
        return img.getHeight();
    }
    
    /**
     * Set the card to clicked
     * @param clicked Whether the card is clicked
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    /**
     * Get the type of the card
     * @return The type of the card
     */
    public int getType() {
        return type;
    }
}

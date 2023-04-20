import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor {
    private GreenfootImage img;
    private int cost, x, y, type;
    private boolean playable, mouseIsDown, clicked, enemy;
    
    public Card(int width, int height, boolean enemy, int type) {this(0, width, height, false, enemy, type);}
    public Card(int cost, int width, int height, boolean playable, boolean enemy, int type) {
        img = new GreenfootImage(width, height);
        img.setColor(Color.GREEN);
        img.fillRect(0, 0, img.getWidth(), img.getHeight());
        img.scale(width, height);
        setImage(img);
        this.cost = cost;
        this.playable = playable;
        this.enemy = enemy;
    }
    
    public void addedToWorld(World w) {
        x = getX();
        y = getY();
    }
    
    public boolean canPlay(int currElixir) {
        return cost <= currElixir;
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (playable) {
            MouseInfo m = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this))  
                mouseIsDown = true;
            else if (Greenfoot.mouseClicked(this)) {
                mouseIsDown = false;
                for (Card c : getWorld().getObjects(Card.class))
                    c.setClicked(false);
                clicked = true;
                if ((!enemy && y - m.getY() >= img.getHeight()) || (enemy && m.getY() - y >= img.getHeight())) { // Play card
                    Card c = ((MainWorld)getWorld()).nextCard(enemy);
                    spawnCard();
                    getWorld().addObject(new Card(1, getWidth(), getHeight(), true, enemy, c.getType()), x, y);
                    getWorld().removeObject(this);
                }
            }
            if (mouseIsDown)
                setLocation(m.getX(), m.getY());
            else if (Greenfoot.mouseMoved(this) || clicked)
                setLocation(x, enemy ? y + 5 : y - 5);
            else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
                setLocation(x, y);
        }
    }
    
    private void spawnCard() {
        int x = FINAL.WORLD_WIDTH / 2 + FINAL.WORLD_WIDTH / 2 * (Math.random() * 2 == 0 ? 1 : -1), y = enemy ? 100 : FINAL.WORLD_HEIGHT - 100;
        switch (type) {
            case 0:
                getWorld().addObject(new Arrows(enemy), x, y);
                break;
            case 1:
                getWorld().addObject(new Fireball(enemy), x, y);
                break;
            case 2:
                getWorld().addObject(new Archers(enemy), x, y);
                break;
            case 3:
                getWorld().addObject(new Giant(enemy), x, y);
                break;
            case 4:
                getWorld().addObject(new Knight(enemy), x, y);
                break;
            case 5:
                getWorld().addObject(new Minion(enemy), x, y);
                break;
            case 6:
                getWorld().addObject(new Musketeer(enemy), x, y);
                break;
        }
    }
    
    public int getWidth() {
        return img.getWidth();
    }
    
    public int getHeight() {
        return img.getHeight();
    }
    
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    public int getType() {
        return type;
    }
}

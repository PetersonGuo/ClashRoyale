import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor {
    private GreenfootImage img;
    private int cost, x, y;
    private boolean playable, mouseIsDown, clicked;
    
    public Card(int width, int height) {this(0, width, height, false);}
    public Card(int cost, int width, int height, boolean playable) {
        img = new GreenfootImage(width, height);
        img.setColor(Color.GREEN);
        img.fillRect(0, 0, img.getWidth(), img.getHeight());
        img.scale(width, height);
        setImage(img);
        this.cost = cost;
        this.playable = playable;
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
                for (Card c : getWorld().getObjects(Card.class)) c.setClicked(false);
                clicked = true;
                if (Math.sqrt(Math.pow(m.getX() - x, 2) + Math.pow(m.getY() - y, 2)) >= img.getHeight() / 2) getWorld().removeObject(this);
            }
            if (mouseIsDown) {
                setLocation(m.getX(), m.getY());
            } else if (Greenfoot.mouseMoved(this) || clicked)
                setLocation(x, y >= FINAL.WORLD_HEIGHT / 2 ? y - 5 : y + 5);
            else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
                setLocation(x, y);
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
}

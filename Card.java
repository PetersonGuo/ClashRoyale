import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor {
    private GreenfootImage img;
    private int cost;
    public Card(int cost) {
        img = new GreenfootImage((FINAL.WORLD_WIDTH - FINAL.CARD_SPACING * 5) / 5, FINAL.WORLD_HEIGHT / 7);
        img.setColor(Color.GREEN);
        img.fillRect(0, 0, img.getWidth(), img.getHeight());
        setImage(img);
        this.cost = cost;
    }
    
    public boolean canPlay(int currElixir) {
        return cost <= currElixir;
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
    
    public int getWidth() {
        return img.getWidth();
    }
    
    public int getHeight() {
        return img.getHeight();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ElixirBar here.
 * Add text animation later :(
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElixirBar extends Actor {
    private int elixir, elixirTimer, sectionSize, elixirTime, maxElixir;
    private GreenfootImage img;
    private Text elixirText;
    public ElixirBar(int startElixir, int maxElixir, int elixirTime) {
        this.maxElixir = maxElixir;
        this.elixirTime = elixirTime;
        elixir = startElixir;
        elixirTimer = 0;
        img = new GreenfootImage(320, 20);
        img.setColor(FINAL.ELIXIR_COLOR);
        img.fillRect(0, 0, 320 / maxElixir * elixir, img.getHeight());
        setImage(img);
    }
    
    public void addedToWorld(World w) {
        elixirText = new Text(elixir, Color.WHITE, 35);
        w.addObject(elixirText, (int)(getX() - img.getWidth() / 2.6), getY() + img.getHeight() / 4 * (getY() < FINAL.WORLD_HEIGHT / 2 ? 1 : -1));
    }
    
    /**
     * Act - do whatever the ElixirBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        elixirTimer++;        
        if (elixirTimer >= (elixirTime * 60)) {
            elixirTimer = 0;
            elixir++;
            if (elixir >= maxElixir)
                elixir = maxElixir;
            elixirText.updateText(elixir);
            img.clear();
            img.setColor(FINAL.ELIXIR_COLOR);
            img.fillRect(0, 0, 320 / maxElixir * elixir, img.getHeight());
            img.setColor(Color.BLACK);
            img.drawLine(0,0,0,img.getHeight());
            img.drawLine(img.getWidth(), 0, img.getWidth(), img.getHeight());
            setImage(img);
        }
    }
}

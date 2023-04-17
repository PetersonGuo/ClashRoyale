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
        drawImg();
        sectionSize = img.getWidth() / maxElixir;
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
        }
        drawImg();
    }
    
    private void drawImg() {
        img.clear();
        // Background Elixir Color
        img.setColor(Color.GRAY);
        if (elixirTimer != 0)
            img.fillRect(0, 0, img.getWidth() / maxElixir * elixir + img.getWidth() / (maxElixir * elixirTime * 60 / elixirTimer), img.getHeight());
        // Main Elixir Color
        img.setColor(FINAL.ELIXIR_COLOR);
        img.fillRect(0, 0, sectionSize * elixir, img.getHeight());
        // Borders
        img.setColor(Color.BLACK);
        img.drawLine(0, 0, img.getWidth() - 1, 0);
        img.drawLine(0, img.getHeight() - 1, img.getWidth() - 1, img.getHeight() - 1);
        img.drawLine(0, 0, 0, img.getHeight());
        img.drawLine(img.getWidth() - 1, 0, img.getWidth() - 1, img.getHeight());
        // Inside Lines
        for (int i = 1; i <= maxElixir; i++)
            img.drawLine(img.getWidth() / maxElixir * i, 0, img.getWidth() / maxElixir * i, img.getHeight());
        setImage(img);
    }
}

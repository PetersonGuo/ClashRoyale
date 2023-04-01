import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class ChooseScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class ChooseScreen extends Worlds {
    // Stores Values to be iterated over
    class SetContainer {
        // Instance Variables
        private Text display;
        private int x, y;
        private boolean stats;
        
        // Initialize variables
        public SetContainer(Text display, int x, int y) {this(display, x, y, false);}        
        public SetContainer(Text display, int x, int y, boolean stats) {
            this.x = x;
            this.y = y;
            this.display = display;
            this.stats = stats;
        }
        
        // Accessors
        public Text getText() {return display;}
        public int getX() {return x;}
        public int getY() {return y;}
        public boolean isStat() {return stats;}
    }
    final static String[] statNames = {
        "Difficulty"
    };
    final Set<SetContainer> display = new HashSet<>() {{
        add(new SetContainer(new Text("Choose variables for the simulation", Color.BLACK, (FINAL.WORLD_WIDTH - 80) * 2 / "Choose variables for the simulation".length()), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 5));
        for (int i = 0; i < statNames.length; i++)
            add(new SetContainer(new Text(statNames[i], Color.BLACK, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 3 + i * FINAL.STAT_SIZE * 3, true));
    }};
    private static Map<String, Integer> stats;
    private static Button cont;
    /**
     * Constructor for objects of class ChooseScreen.
     * 
     */
    public ChooseScreen() {
        initializeStats();
        for (SetContainer setValue : display) {
            addObject(setValue.getText(), setValue.getX(), setValue.getY());
            if (setValue.isStat()) {
                int y = setValue.getY() + FINAL.STAT_SIZE + FINAL.ARROW_OFFSET;
                addObject(new Text(stats.get(setValue.getText().getString()), Color.BLACK, 30), FINAL.WORLD_WIDTH / 2, y);
                addObject(new Arrow(true, setValue.getText().getString()), FINAL.WORLD_WIDTH / 4, y);
                addObject(new Arrow(false, setValue.getText().getString()), FINAL.WORLD_WIDTH * 3 / 4, y);
            }
        }
        
        cont = new Button("Continue");
        addObject(cont, FINAL.WORLD_WIDTH - cont.getWidth() / 2 - 20, FINAL.WORLD_HEIGHT - cont.getHeight() / 2);
    }
    
    private void initializeStats() {
        stats = new HashMap<>() {{
            for (String i : statNames)
                put(i, 0);
        }};
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(cont))
            nextWorld();
    }
    
    public void setValue(Arrow selector) {
        stats.put(selector.getSelector(), stats.get(selector.getSelector() + (selector.isLeft() ? -1 : 1)));
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new MainWorld());
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
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
        private Chevron left, right;
        private int x, y, min;
        
        // Initialize variables
        public SetContainer(Text display, int x, int y) {this(display, x, y, null, null);}
        public SetContainer(Text display, int x, int y, Chevron left, Chevron right) {
            this.x = x;
            this.y = y;
            this.display = display;
            this.left = left;
            this.right = right;
        }
        
        // Accessors
        public Text getText() {return display;}
        public int getX() {return x;}
        public int getY() {return y;}
    }
    
    /**
     * Tuple to store values held by the stats
     */
    class Tuple {
        double value, min, max, inc;
        Tuple(double value, double min, double max, double inc) {
            this.value = value;
            this.min = min;
            this.max = max;
            this.inc = inc;
        }
    }
    private final int statsPerPage = FINAL.STAT_SECTION_SIZE / (FINAL.STAT_SIZE + FINAL.ARROW_OFFSET); // Number of stats per page
    private final List<SetContainer> statText = new ArrayList<>() {{ // Add stats to a list
        for (int i = 0; i < FINAL.STAT_NAMES.length; i++)
            add(new SetContainer(new Text(FINAL.STAT_NAMES[i], Color.BLACK, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 3 + (i % statsPerPage) * (FINAL.STAT_SIZE + FINAL.ARROW_OFFSET) * 2));
    }};
    private final Set<SetContainer> staticText = new HashSet<>() {{ // Text that doesnt change
        add(new SetContainer(new Text("Choose variables for the simulation", Color.BLACK, FINAL.WORLD_WIDTH / "Choose variables for the simulation".length() * 2), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 5));
    }};
    private static int pageNum, act; // Page number and act
    private boolean lastPress; // Last key press
    private static Map<String, Tuple> stats; // Map of stats
    private static Button cont; // Continue button
    private static Chevron left, right; // Left and right chevrons
    /**
     * Constructor for objects of class ChooseScreen.
     * 
     */
    public ChooseScreen() {
        pageNum = act = 0; // Initialize variables
        lastPress = false;
        initializeStats(); // Initialize stats
        updateStatPage(); // Update the page
        
        cont = new Button("Continue"); // Initialize buttons
        addObject(cont, FINAL.WORLD_WIDTH - cont.getWidth() / 2 - 20, FINAL.WORLD_HEIGHT - cont.getHeight() / 2); // Add buttons
    }
    
    /**
     * Initialize stats
     */
    private void initializeStats() { // Add stats to map
        stats = new HashMap<>() {{ // Initialize map
            for (int i = 0; i < FINAL.STAT_NAMES.length; i++) // Add stats
                put(FINAL.STAT_NAMES[i], new Tuple(FINAL.DEFAULT_VALUES[i][0], FINAL.DEFAULT_VALUES[i][1], FINAL.DEFAULT_VALUES[i][2], FINAL.DEFAULT_VALUES[i][3])); // Add default values
        }};
    }
    
    /**
     * Act - do whatever the ChooseScreen wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.mouseClicked(cont) || (Greenfoot.isKeyDown(FINAL.NEXT_WORLD_BUTTON) && act >= 30)) // Prevents world skipping by adding a .5s delay
            nextWorld(); // Go to next world
        else if (Greenfoot.mouseClicked(left) && pageNum > 0) { // If the left chevron is clicked, go to the previous page
            pageNum--;
            updateStatPage();
        } else if (Greenfoot.mouseClicked(right) && pageNum < statText.size() / statsPerPage) { // If the right chevron is clicked, go to the next page
            pageNum++;
            updateStatPage();
        } else if (Greenfoot.isKeyDown("Left") && !lastPress && pageNum > 0) { // If the left key is pressed, go to the previous page
            pageNum--;
            updateStatPage();
        } else if (Greenfoot.isKeyDown("Right") && !lastPress && pageNum < statText.size() / statsPerPage) { // If the right key is pressed, go to the next page
            pageNum++;
            updateStatPage();
        }
        act++;
        lastPress = Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("Right"); // Update last key press
    }
    
    /**
     * Returns the value of a stat
     * 
     * @param stat The stat to get the value of
     * @return The value of the stat
     */
    private void updateStatPage() { // Update values of the page
        removeObjects(getObjects(Text.class)); // Remove all text
        removeObjects(getObjects(Chevron.class)); // Remove all chevrons
        for (SetContainer setValue : staticText) // Add static text
            addObject(setValue.getText(), setValue.getX(), setValue.getY()); 
        for (int i = pageNum * statsPerPage; i < (pageNum + 1) * statsPerPage && i < statText.size(); i++) { // Add stats
            SetContainer setValue = statText.get(i); // Get the stat
            addObject(setValue.getText(), setValue.getX(), setValue.getY()); // Add the stat
            int y = setValue.getY() + FINAL.STAT_SIZE + FINAL.ARROW_OFFSET; // Get the y value
            Text t = new Text(stats.get(setValue.getText().getString()).value, Color.BLACK, FINAL.STAT_SIZE); // Get the text
            addObject(t, FINAL.WORLD_WIDTH / 2, y); // Add the text
            addObject(new Chevron(true, setValue.getText().getString(), FINAL.STAT_SIZE, t), FINAL.WORLD_WIDTH / 4, y); // Add the chevrons
            addObject(new Chevron(false, setValue.getText().getString(), FINAL.STAT_SIZE, t), FINAL.WORLD_WIDTH * 3 / 4, y); // Add the chevrons
        }
        left = new Chevron(true, null, FINAL.STAT_SIZE); // Add the chevrons
        right = new Chevron(false, null, FINAL.STAT_SIZE); // Add the chevrons
        addObject(new Text((pageNum + 1) + " / " + (statText.size() / statsPerPage + 1), Color.BLACK, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT * 9 / 10); // Add the page number
        addObject(left, FINAL.WORLD_WIDTH * 2 / 5, FINAL.WORLD_HEIGHT * 9 / 10); // Add the chevrons
        addObject(right, FINAL.WORLD_WIDTH * 3 / 5, FINAL.WORLD_HEIGHT * 9 / 10); // Add the chevrons
    }
    
    /**
     * Returns the value of a stat
     * 
     * @param stat The stat to get the value of
     * @return The value of the stat
     */
    public void setValue(Chevron selector) {
        if ((!selector.isLeft() || stats.get(selector.getSelector()).value > stats.get(selector.getSelector()).min) && (selector.isLeft() || stats.get(selector.getSelector()).value < stats.get(selector.getSelector()).max)) // If the value is within the range
            stats.get(selector.getSelector()).value += (selector.isLeft() ? -stats.get(selector.getSelector()).inc : stats.get(selector.getSelector()).inc);
        selector.getText().updateText(stats.get(selector.getSelector()).value); // Update the text
    }
    
    /**
     * Next world
     */
    public void nextWorld() {
        Map<String, Double> stats = new HashMap<>();
        for (Map.Entry<String, Tuple> m : this.stats.entrySet()) 
            stats.put(m.getKey(), m.getValue().value);
        Greenfoot.setWorld(new MainWorld(stats));
    }
}
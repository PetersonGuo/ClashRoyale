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
        private int x, y;
        
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
    private final int statsPerPage = FINAL.STAT_SECTION_SIZE / (FINAL.STAT_SIZE + FINAL.ARROW_OFFSET);
    private final List<SetContainer> statText = new ArrayList<>() {{
        for (int i = 0; i < FINAL.STAT_NAMES.length; i++)
            add(new SetContainer(new Text(FINAL.STAT_NAMES[i], Color.WHITE, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 3 + (i % statsPerPage) * (FINAL.STAT_SIZE + FINAL.ARROW_OFFSET) * 2));
    }};
    private final Set<SetContainer> staticText = new HashSet<>() {{
        add(new SetContainer(new Text("Choose variables for the simulation", Color.WHITE, FINAL.WORLD_WIDTH / "Choose variables for the simulation".length() * 2), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 5));
    }};
    private static int pageNum, act;
    private boolean lastPress;
    private static Map<String, Integer> stats;
    private static Button cont;
    private static Chevron left, right;
    /**
     * Constructor for objects of class ChooseScreen.
     * 
     */
    public ChooseScreen() {
        pageNum = act = 0;
        lastPress = false;
        initializeStats();
        updateStatPage();
        
        
        cont = new Button("Continue");
        addObject(cont, FINAL.WORLD_WIDTH - cont.getWidth() / 2 - 20, FINAL.WORLD_HEIGHT - cont.getHeight() / 2);
    }
    
    private void initializeStats() {
        stats = new HashMap<>() {{
            for (int i = 0; i < FINAL.STAT_NAMES.length; i++)
                put(FINAL.STAT_NAMES[i], FINAL.DEFAULT_VALUES[i]);
        }};
    }
    // Add Nametag
    public void act() {
        if (Greenfoot.mouseClicked(cont) || (Greenfoot.isKeyDown(FINAL.NEXT_WORLD_BUTTON) && act >= 30)) // Prevents world skipping by adding a .5s delay
            nextWorld();
        else if (Greenfoot.mouseClicked(left) && pageNum > 0) {
            pageNum--;
            updateStatPage();
        } else if (Greenfoot.mouseClicked(right) && pageNum < statText.size() / statsPerPage) {
            pageNum++;
            updateStatPage();
        } else if (Greenfoot.isKeyDown("Left") && !lastPress && pageNum > 0) {
            pageNum--;
            updateStatPage();
        } else if (Greenfoot.isKeyDown("Right") && !lastPress && pageNum < statText.size() / statsPerPage) {
            pageNum++;
            updateStatPage();
        }
        act++;
        lastPress = Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("Right");
    }
    
    private void updateStatPage() {
        removeObjects(getObjects(Text.class));
        removeObjects(getObjects(Chevron.class));
        for (SetContainer setValue : staticText)
            addObject(setValue.getText(), setValue.getX(), setValue.getY());
        for (int i = pageNum * statsPerPage; i < (pageNum + 1) * statsPerPage && i < statText.size(); i++) {
            SetContainer setValue = statText.get(i);
            addObject(setValue.getText(), setValue.getX(), setValue.getY());
            int y = setValue.getY() + FINAL.STAT_SIZE + FINAL.ARROW_OFFSET;
            Text t = new Text(stats.get(setValue.getText().getString()), Color.WHITE, FINAL.STAT_SIZE);
            addObject(t, FINAL.WORLD_WIDTH / 2, y);
            addObject(new Chevron(true, setValue.getText().getString(), FINAL.STAT_SIZE, t), FINAL.WORLD_WIDTH / 4, y);
            addObject(new Chevron(false, setValue.getText().getString(), FINAL.STAT_SIZE, t), FINAL.WORLD_WIDTH * 3 / 4, y);
        }
        left = new Chevron(true, null, FINAL.STAT_SIZE);
        right = new Chevron(false, null, FINAL.STAT_SIZE);
        addObject(new Text((pageNum + 1) + " / " + (statText.size() / statsPerPage + 1), Color.WHITE, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT * 9 / 10);
        addObject(left, FINAL.WORLD_WIDTH * 2 / 5, FINAL.WORLD_HEIGHT * 9 / 10);
        addObject(right, FINAL.WORLD_WIDTH * 3 / 5, FINAL.WORLD_HEIGHT * 9 / 10);
    }
    
    public void setValue(Chevron selector) {
        stats.put(selector.getSelector(), stats.get(selector.getSelector()) + (selector.isLeft() ? -1 : 1));
        selector.getText().updateText(stats.get(selector.getSelector()));
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new MainWorld(stats));
    }
}
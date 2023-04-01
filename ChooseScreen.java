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
        private Arrow left, right;
        private int x, y;
        
        // Initialize variables
        public SetContainer(Text display, int x, int y) {this(display, x, y, null, null);}
        public SetContainer(Text display, int x, int y, Arrow left, Arrow right) {
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
    private final int statsPerPage = FINAL.WORLD_HEIGHT / 4 / (FINAL.STAT_SIZE + FINAL.ARROW_OFFSET);
    private final static String[] statNames = {
        "Difficulty0",
        "Difficulty1",
        "Difficulty2",
        "Difficulty3",
        "Difficulty4",
        "Difficulty5",
        "Difficulty6",
        "Difficulty7",
        "Difficulty8",
    };
    private final List<SetContainer> statText = new ArrayList<>() {{
        for (int i = 0; i < statNames.length; i++)
            add(new SetContainer(new Text(statNames[i], Color.BLACK, FINAL.STAT_SIZE), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 3 + (i % statsPerPage) * FINAL.STAT_SIZE * 3));
    }};
    private final Set<SetContainer> staticText = new HashSet<>() {{
        add(new SetContainer(new Text("Choose variables for the simulation", Color.BLACK, (FINAL.WORLD_WIDTH - 80) * 2 / "Choose variables for the simulation".length()), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT / 5));
    }};
    private static int pageNum;
    private static Map<String, Integer> stats;
    private static Button cont;
    private static Arrow left, right;
    /**
     * Constructor for objects of class ChooseScreen.
     * 
     */
    public ChooseScreen() {
        pageNum = 0;
        initializeStats();
        updateStatPage();
        
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
        else if (Greenfoot.mouseClicked(left) && pageNum > 0) {
            pageNum--;
            updateStatPage();
        } else if (Greenfoot.mouseClicked(right) && pageNum < statText.size() / statsPerPage) {
            pageNum++;
            updateStatPage();
        }
    }
    
    private void updateStatPage() {
        removeObjects(getObjects(Text.class));
        removeObjects(getObjects(Arrow.class));
        for (SetContainer setValue : staticText)
            addObject(setValue.getText(), setValue.getX(), setValue.getY());
        for (int i = pageNum * statsPerPage; i < (pageNum + 1) * statsPerPage && i < statText.size(); i++) {
            SetContainer setValue = statText.get(i);
            addObject(setValue.getText(), setValue.getX(), setValue.getY());
            int y = setValue.getY() + FINAL.STAT_SIZE + FINAL.ARROW_OFFSET;
            Text t = new Text(stats.get(setValue.getText().getString()), Color.BLACK, 30);
            addObject(t, FINAL.WORLD_WIDTH / 2, y); // Update text
            addObject(new Arrow(true, setValue.getText().getString(), t), FINAL.WORLD_WIDTH / 4, y);
            addObject(new Arrow(false, setValue.getText().getString(), t), FINAL.WORLD_WIDTH * 3 / 4, y);
        }
        left = new Arrow(true, null);
        right = new Arrow(false, null);
        addObject(new Text((pageNum + 1) + " / " + (statText.size() / statsPerPage + 1), Color.BLACK, 30), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT - 75);
        addObject(left, FINAL.WORLD_WIDTH * 2 / 5, FINAL.WORLD_HEIGHT - 75);
        addObject(right, FINAL.WORLD_WIDTH * 3 / 5, FINAL.WORLD_HEIGHT - 75);
    }
    
    public void setValue(Arrow selector) {
        stats.put(selector.getSelector(), stats.get(selector.getSelector()) + (selector.isLeft() ? -1 : 1));
        selector.getText().updateText(stats.get(selector.getSelector()));
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new MainWorld());
    }
}
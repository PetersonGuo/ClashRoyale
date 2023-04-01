import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class ChooseScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class ChooseScreen extends Worlds {
    private static Button cont;
    static Arrow[][] rows;
    static Map<String, Integer> stats;
    final static String[] display = {
        "Choose the starting values for each team!",
        "Difficulty"
    };
    /**
     * Constructor for objects of class ChooseScreen.
     * 
     */
    public ChooseScreen() {    
        stats = new HashMap<>();
        
        addObject(new Text (display[0], Color.BLACK, FINAL_VARS.WORLD_WIDTH * 2 / display[0].length()), FINAL_VARS.WORLD_WIDTH / 2, 230);
        addObject(new Text (display[1], Color.BLACK, 30), FINAL_VARS.WORLD_WIDTH / 2, 465);
        
        cont = new Button ("Continue");
        addObject(cont, FINAL_VARS.WORLD_WIDTH - cont.getWidth() / 2 - 20, FINAL_VARS.WORLD_HEIGHT - cont.getHeight() / 2);
        
        // Add LR Arrows
    }
    
    public void setValue(int point) {
        
    }
    
    public void nextWorld() {
        Greenfoot.setWorld(new MainWorld());
    }
}
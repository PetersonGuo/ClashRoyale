import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class EndScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class EndScreen extends Worlds {
    private int towersLeft,towerHp, towerHp2, allyTowers, EnemyTowers; // The number of towers left, the hp of the towers, the hp of the towers, the number of ally towers, the number of enemy towers
    private GreenfootImage end; // The image of the end screen
    /**
     * Constructor for objects of class EndScreen.
     * @param crowns The number of crowns each team has
     */
    public EndScreen(int[] crowns) {
        int allyCrowns = crowns[0];
        int enemyCrowns = crowns[1];
        if (allyCrowns == enemyCrowns){
            end = new GreenfootImage("draw.png");
            addObject(new Text(allyCrowns + " - " + enemyCrowns, Color.BLACK, 50), FINAL.WORLD_WIDTH/2, FINAL.WORLD_HEIGHT / 3);
        }else if(allyCrowns > enemyCrowns){
            end = new GreenfootImage("blueWin.png");
            addObject(new Text(allyCrowns + " - " + enemyCrowns, Color.BLUE, 50), FINAL.WORLD_WIDTH/2, FINAL.WORLD_HEIGHT / 3);
        }else{
            end = new GreenfootImage("redWin.png");
            addObject(new Text(enemyCrowns + " - " + allyCrowns, Color.RED, 50), FINAL.WORLD_WIDTH/2, FINAL.WORLD_HEIGHT / 3);
        }
        
        end.scale(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT);
        setBackground(end);
        
        addObject(new Text("Press '" + FINAL.NEXT_WORLD_BUTTON + "' to continue", Color.BLACK, 30), FINAL.WORLD_WIDTH / 2, FINAL.WORLD_HEIGHT * 3 / 4);
    }
    
    /**
     * act - Check if the user wants to go to the next world
     */
    public void act() {
        if (Greenfoot.isKeyDown(FINAL.NEXT_WORLD_BUTTON)) {
            nextWorld();
        }
    }
    
    /**
     * nextWorld - Go to the next world
     */
    public void nextWorld() {
        Greenfoot.setWorld(new ChooseScreen());
    }

}

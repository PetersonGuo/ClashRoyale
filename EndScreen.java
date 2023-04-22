import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class EndScreen here.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public class EndScreen extends Worlds {
    private int towersLeft,towerHp, towerHp2, allyTowers, EnemyTowers;
    private GreenfootImage end;
    /**
     * Constructor for objects of class EndScreen.
     */
    public EndScreen(int[] crowns) {
        ArrayList<Towers> towersLeft = (ArrayList<Towers>)getObjects(Towers.class);
        for (Towers t : towersLeft) {
            if (t.isAlly()) {
                allyTowers++;
                towerHp += t.getHp();
            } else if (!t.isAlly()) {
                EnemyTowers++;
                towerHp2 += t.getHp();
            }
        }
        
        if (allyTowers == EnemyTowers){
            if (towerHp > towerHp2){
                end = new GreenfootImage("blueWin.png");
            }else if (towerHp2 > towerHp){
                end = new GreenfootImage("redWin.png");
            }else{
                end = new GreenfootImage("draw.png");
            }
        }else if(allyTowers > EnemyTowers){
            end = new GreenfootImage("blueWin.png");
        }else{
            end = new GreenfootImage("redWin.png");
        }
        
        end.scale(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT);
        setBackground(end);
    }
    
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

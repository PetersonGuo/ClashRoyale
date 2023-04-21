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
    public EndScreen() {
        ArrayList<Towers> towersLeft = (ArrayList<Towers>)getObjects(Towers.class);
        for (Towers t : towersLeft){
            if (t.isAlly()) {
                allyTowers++;
                towerHp += t.getHp();
            } else if(!t.isAlly()) {
                EnemyTowers++;
                towerHp2 += t.getHp();
            }
        }
        
        if (allyTowers == EnemyTowers){
            if (towerHp > towerHp2 && allyTowers == 3){
                //blue side wins by having more hp
                GreenfootImage end = new GreenfootImage("crBlue1Crown.png");
            }else if(towerHp2 > towerHp && allyTowers == 3){
                //red side wins by having more hp
                GreenfootImage end = new GreenfootImage("crRed1Crown.png");
            }else if(towerHp > towerHp2 && allyTowers == 2){
                //blue side wins by having more hp
                GreenfootImage end = new GreenfootImage("crBlue2Crown.png");
            }else if(towerHp2 > towerHp && allyTowers == 2){
                //red side wins by having more hp
                GreenfootImage end = new GreenfootImage("crRed2Crown.png");
            }else if(towerHp > towerHp2 && allyTowers == 1){
                //blue side wins by having more hp
                GreenfootImage end = new GreenfootImage("crBlue3Crown.png");
            }else if(towerHp2 > towerHp && allyTowers == 1){
                //red side wins by having more hp
                GreenfootImage end = new GreenfootImage("crRed3Crown.png");
            }else{
                //tie
                GreenfootImage end = new GreenfootImage("crDraw.png");
            }
        }else if(allyTowers > EnemyTowers && EnemyTowers == 2){
            //blue side wins by 1 crown
            GreenfootImage end = new GreenfootImage("crBlue1Crown.png");
        }else if(allyTowers > EnemyTowers && EnemyTowers == 1){
            //blue side wins by 2 crown
            GreenfootImage end = new GreenfootImage("crBlue2Crown.png");
        }else if(allyTowers > EnemyTowers && EnemyTowers == 0){
            //blue side wins by 3 crown
            GreenfootImage end = new GreenfootImage("crBlue3Crown.png");
        }else if(EnemyTowers > allyTowers && allyTowers == 2){
            //red side wins by 1 crown
            GreenfootImage end = new GreenfootImage("crRed1Crown.png");
        }else if(EnemyTowers > allyTowers && allyTowers == 1){
            //red side wins by 2 crown
            GreenfootImage end = new GreenfootImage("crRed2Crown.png");
        }else if(EnemyTowers > allyTowers && allyTowers == 0){
            //red side wins by 3 crown
            GreenfootImage end = new GreenfootImage("crRed3Crown.png");
        }
        
        end.scale(FINAL.WORLD_WIDTH, FINAL.WORLD_HEIGHT);
        setBackground(end);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")){
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

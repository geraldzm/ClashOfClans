/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.model;

import com.game.model.Fighter;
import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author andro
 */
public class Defense extends Fighter {

    protected int health;
    private int troops;
    private int appearanceLevel;
    protected final int frames = 10; //cada 30 frames intenta atacar
    protected int framesTimer=0;
    protected Point nextMove;
    
    public Defense(int x, int y, String imgPath, GameBoard gameBoard, HandlerGameObjects handlerGameObjects){
        super(x, y, 40, 40, ID.DEFENSE, Team.DEFENSE, gameBoard, handlerGameObjects);
        range = 3;
        strokePerTime = 3;
        
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }
    
    @Override
    public void upgrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
    
    @Override
    public void tick() {
        if(framesTimer == frames){

            if(isSomeoneInRange()) attack();
            
            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }
    
    
    @Override
    public boolean isSomeoneInRange() {
        boolean isTargetOk = target != null && !target.isDead();
        
        if(isTargetOk && isInRange(target.getLocation())) return true;
            
        ArrayList<Warrior> warriors = gameBoard.getWarriors();
        
        if(warriors == null || warriors.isEmpty())return false;

        for (int i = 0; i < warriors.size(); i++) { // buscamos el mas cercano
            if(isInRange(warriors.get(i).getLocation())){
                target = warriors.get(i);
                return true;
            }
        }
        
        target = null; // no hab[ia nadie en el rango
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import java.util.Date;

/**
 *
 * @author andro
 */
public class Bomb extends LandDefense{
    
    public Bomb(int x, int y, String imgPath, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, imgPath, gameBoard, handlerGameObjects);
        
        strokePerTime = 30;
        range = 1;
    }
    
    // Le hago override pues la bomba cuando explota, muere
    @Override
    public void attack(){
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);

            die();
        }
    }

    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }
}

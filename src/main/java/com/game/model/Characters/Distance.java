package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import java.awt.*;
import java.util.Date;

public class Distance extends Warrior {

    public Distance(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, ID.DISTANCE, team, gameBoard, handlerGameObjects);
        range = 3;
        strokePerTime = 2;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }

    // Constructor para el beast
    protected Distance(int x, int y, ID id, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, id, team, gameBoard, handlerGameObjects);
        range = 3;
        strokePerTime = 2;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }

    @Override
    public Point heuristic() {
        return null;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void attack(){ // esta mae tira flechas, luego
        if(target == null) System.out.println("wtf");
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);
            // System.out.println("de: " +getLocation());

            timer = new Date();
        }
    }
}

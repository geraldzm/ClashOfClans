package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.Tools;
import com.game.model.Warrior;
import java.awt.Image;
import java.awt.Point;
import java.util.Date;

public class Air extends Warrior {
    
    private static String attackSound = "dragon_deploy_01.wav";

    public Air(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, ID.AIR, team, gameBoard, handlerGameObjects);
        range = 5;
        strokePerTime = 4;
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
    public void move(){
        // El no se mueve :)
    }

    @Override
    public void attack(){ // La animacion de la bola de fuego
        if(target == null) System.out.println("wtf");
        
        
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);
            // System.out.println("de: " +getLocation());

            timer = new Date();
            Tools.playSound(attackSound);
        }
    }
}

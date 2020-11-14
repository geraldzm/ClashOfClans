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

public class AirWarrior extends Warrior {
    
    private static String attackSound = "dragon_deploy_01.wav";
    private boolean moved = false;
    
    public AirWarrior(String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(0, 0, 40, 40, ID.AIR, team, gameBoard, handlerGameObjects);
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
    public void tick() {
        // Los air warrior deben aparecer al lado de su primer objetivo segun las especificaciones
        // Also makes sense porque no se mueven y si solo quedan dos dragones pues F nadie gana
        if (!moved){
            move();
            moved = true;
        }
        
        if(framesTimer == frames){

            if(isSomeoneInRange()) attack();

            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }
    
    @Override
    public void move(){
        Warrior warrior = gameBoard.getRandom(getTeam());
        
        if (warrior == null) return;
        
        Point pos;
        
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                pos = new Point(warrior.getLocation().x + i, warrior.getLocation().y + j);

                if (!gameBoard.isPositionOccupied(pos)){
                    gameBoard.moveCharacter(getLocation(), pos);
                    setLocation(pos);
                    break;
                }
            }
        } 
    }

    @Override
    public void attack(){ 
        // La animacion de la bola de fuego
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);

            timer = new Date();
            Tools.playSound(attackSound);
        }
    }
}

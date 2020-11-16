package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.Tools;
import com.game.model.Warrior;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;


public class AirWarrior extends Warrior {
    
    private static String attackSound = "dragon_deploy_01.wav";
    private boolean deployed = false;

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * @param images debe haber al menos 1 imagen, la primera imagen es por defecto
     * */
    public AirWarrior(int maxHealth, String name, int troops, int appearanceLevel, int range, int strokePerTime, int speed, ImageIcon[] images) {
        super(ID.AIR, name, maxHealth, troops, appearanceLevel, range, strokePerTime, speed, images);
    }

    /**
     * <h1>para clonar</h1>
     * */
    public AirWarrior(Warrior airWarrior, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(airWarrior, gameBoard, handlerGameObjects);
    }

    @Override
    public Point heuristic() {
        return null;
    }

    @Override
    public void upgrade(int level) {
        
    }

    @Override
    public void levelUp() {}

    @Override
    public void tick() {
        // Los air warrior deben aparecer al lado de su primer objetivo segun las especificaciones
        // Also makes sense porque no se mueven y si solo quedan dos dragones pues F nadie gana
        if (!deployed){
            deployed = true;
            move();
            return;
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
        ArrayList<Warrior> warriors = gameBoard.getTeam(getTeam());

        if(warriors == null || warriors.isEmpty()) return; // no hay nadie

        Warrior warrior = warriors.get(random.nextInt(warriors.size())); // sacamos un enemigo random

        Point pos;
        
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                pos = new Point(warrior.getLocation().x + i, warrior.getLocation().y + j);
                if (!gameBoard.isPositionOccupied(pos)){
                    setLocation(pos);
                    gameBoard.addCharacter(this);
                    return;
                }
            }
        }

        deployed = false; // no se pudo colocar
    }

    @Override
    public Warrior clone(Warrior w) {
        return new AirWarrior(w, w.getGameBoard(), w.getHandlerGameObjects());
    }

    @Override
    public void attack(){
        super.attack();
        // La animacion de la bola de fuego
    }

    @Override
    public void makeSound() {
        Tools.playSound(attackSound);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.effects.Explosion;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andro
 */
public class Bomb extends Fighter {

    private ArrayList<Warrior> targets;

    public Bomb(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Bomba", ID.DEFENSE, Team.DEFENSE, 1, 50, level, 1, "Bomb.png", gameBoard, handlerGameObjects);
        targets = new ArrayList<>();
    }

    @Override
    public void upgrade(int level) {

    }

    @Override
    public void levelUp() {

    }

    // Le hago override pues la bomba cuando explota, muere
    @Override
    public void attack(){
        if (new Date().getTime() - timer.getTime() >= cooldown){
            //explotion
            new Explosion(getX(), getY(), ID.EXPLOSION, getHandlerGameObjects());
            for (int i = 0; i < targets.size(); i++)targets.get(i).hit(strokePerTime);
            die();
        }
    }

    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }


    @Override
    public boolean isSomeoneInRange() {

        if(targets != null && !targets.isEmpty()) return true;

        boolean rs = false;

        ArrayList<Warrior> warriors = gameBoard.getTeam(getTeam());

        if(warriors == null || warriors.isEmpty())return false;

        for (int i = 0; i < warriors.size(); i++){
            if(getTargetCriteria().apply(warriors.get(i))){
                targets.add(warriors.get(i));
                rs = true;
            }
        }

        return rs;
    }

    @Override
    public void makeSound() {

    }
}

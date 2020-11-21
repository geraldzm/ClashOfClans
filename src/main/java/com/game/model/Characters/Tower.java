/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.effects.ArrowBullet;

import java.util.ArrayList;

/**
 *
 * @author andro
 */
public class Tower extends Fighter {
    
    public Tower(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Torre", ID.DEFENSE, Team.DEFENSE, 5, 1, level, 1, "Archer_Tower.png", gameBoard, handlerGameObjects);
    }

    @Override
    public void upgrade(int level) {

    }

    @Override
    public void levelUp() {

    }

    @Override
    public void makeSound() {

    }

    @Override
    public void attackAnimation() {
        new ArrowBullet(getX(), getY(), target, strokePerTime, handlerGameObjects);
    }
}
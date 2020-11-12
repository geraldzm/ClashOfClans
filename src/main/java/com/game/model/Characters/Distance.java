package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.Warrior;

import java.awt.*;

public class Distance extends Warrior {
    public Distance(int x, int y, String imgPath, int width, int height, ID id, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, width, height, id, team, gameBoard, handlerGameObjects);
        range = 3;
        strokePerTime = 1;
    }

    @Override
    public void hit(int damage) {
        reduceHealth(damage);
    }

    @Override
    public void die() {

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

    }

    @Override
    public void move() {

    }
}

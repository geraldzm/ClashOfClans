package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.Warrior;
import com.game.utils.Node;

import java.util.ArrayList;

public class Distance extends Warrior {
    public Distance(int x, int y, int width, int height, ID id, Team team, GameBoard gameBoard) {
        super(x, y, width, height, id, team, gameBoard);
    }

    @Override
    public void hit(int damage) {
        reduceHealth(damage);
    }

    @Override
    public void die() {

    }

    @Override
    public ArrayList<Node> heuristic() {
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

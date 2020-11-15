package com.game.model.Characters;

import com.game.model.Fighter;
import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;

public class Mortar extends Fighter {

    public Mortar(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Mortero", ID.AIR, Team.DEFENSE, 1, 10, level, 1, "Mortar.png", gameBoard, handlerGameObjects);
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
}

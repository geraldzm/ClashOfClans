package com.game.model.Characters;

import com.game.model.Fighter;
import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.effects.MortarBullet;

public class Mortar extends Fighter {

    public Mortar(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Mortero", ID.AIR, Team.DEFENSE, 1, 20, level, 1, "Mortar.png", gameBoard, handlerGameObjects);
    }

    @Override
    public void upgrade(int level) {
        if (level > 5 || level == 1) return;

        strokePerTime++;
        range++;
    }

    @Override
    public void levelUp() {
        if (getLevel() > 5 || getLevel() == 1) return;

        strokePerTime++;
        range++;
    }

    @Override
    public void attackAnimation() {
        new MortarBullet(getX(), getY(), target, strokePerTime, handlerGameObjects);
    }

    @Override
    public void makeSound() {

    }
}

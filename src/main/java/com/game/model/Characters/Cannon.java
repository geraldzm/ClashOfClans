package com.game.model.Characters;

import com.game.model.Fighter;
import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.effects.Bullet;
import com.game.model.effects.CannonBullet;

public class Cannon extends Fighter {

    public Cannon(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Cañón", ID.DEFENSE, Team.DEFENSE, 3, 5, level, 1, "Cannon.png", gameBoard, handlerGameObjects);
    }

    @Override
    public void upgrade(int level) {

    }

    @Override
    public void levelUp() {

    }

    @Override
    public void attackAnimation() {
        new CannonBullet(getX(), getY(), target, strokePerTime, handlerGameObjects);
    }

    @Override
    public void makeSound() {

    }



}

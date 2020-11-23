package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

public class FireBallBullet extends Bullet{
    public FireBallBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 25, 25, ID.BULLET,"fireBall.png", target, damage, handlerGameObjects);
        setRotationPosition(10, 15);
    }

}

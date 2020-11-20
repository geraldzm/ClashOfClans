package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

public class FireBallBullet extends Bullet{
    public FireBallBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 10, 10, ID.BULLET, target, damage, handlerGameObjects);
    }

}

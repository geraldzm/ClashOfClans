package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

public class MortarBullet extends Bullet{
    public MortarBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 15,15, ID.BULLET, target, damage, handlerGameObjects);
    }

}

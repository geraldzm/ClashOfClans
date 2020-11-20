package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

public class ArrowBullet  extends Bullet{
    public ArrowBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 10, 5, ID.BULLET, target, damage, handlerGameObjects);
    }
}

package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

import java.awt.*;

public class CannonBullet extends Bullet{
    public CannonBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 5,5, ID.BULLET, target, damage, handlerGameObjects);
    }
}

package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.effects.FireBallBullet;

import java.util.ArrayList;

public class AirDefense extends Fighter {
    
    public AirDefense(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Defensa aérea", ID.DEFENSE, Team.DEFENSE, 8, 4, level, 1, "Air_Defense.png", gameBoard, handlerGameObjects);
        setTargetCriteria(this::apply);
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
    public void makeSound() {

    }

    private Boolean apply(Warrior warrior) {
        return warrior.getId() == ID.AIR && isInRange(warrior.getLocation());
    }

    @Override
    public void attackAnimation() {
        new FireBallBullet(getX(), getY(), target, strokePerTime, handlerGameObjects);
    }

}

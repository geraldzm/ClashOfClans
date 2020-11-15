package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import java.util.ArrayList;

public class AirDefense extends Fighter {
    
    public AirDefense(int x, int y, int level, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, "Defensa aérea", ID.DEFENSE, Team.DEFENSE, 8, 2, level, 1, "Air_Defense.png", gameBoard, handlerGameObjects);
        setTargetCriteria(this::apply);
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

    private Boolean apply(Warrior warrior) {
        return warrior.getId() == ID.AIR && isInRange(warrior.getLocation());
    }
}

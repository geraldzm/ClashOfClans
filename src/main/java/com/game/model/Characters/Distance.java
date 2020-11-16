package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import javax.swing.*;
import java.awt.*;

public class Distance extends Warrior {

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * @param images debe haber al menos 1 imagen, la primera imagen es por defecto
     * */
    public Distance(int maxHealth, String name, int troops, int appearanceLevel, int range, int strokePerTime, int speed, ImageIcon[] images) {
        super(ID.DISTANCE, name, maxHealth, troops, appearanceLevel, range, strokePerTime, speed, images);
    }

    /**
     * <h1>para clonar</h1>
     * */
    public Distance(Warrior distance, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(distance, gameBoard, handlerGameObjects);
    }

    @Override
    public Point heuristic() {
        return null;
    }

    @Override
    public Warrior clone(Warrior w) {
        return new Distance(w, w.getGameBoard(), w.getHandlerGameObjects());
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

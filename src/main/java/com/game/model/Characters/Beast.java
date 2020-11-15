package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;

import javax.swing.*;

public class Beast extends Distance {

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * @param images debe haber al menos 1 imagen, la primera imagen es por defecto
     * */
    public Beast(int maxHealth, int troops, int appearanceLevel, int range, int strokePerTime, int speed, ImageIcon[] images) {
        super(maxHealth, troops, appearanceLevel, range, strokePerTime, speed, images);
        setId(ID.BEAST);
    }

    @Override
    public void upgrade(int level) {
        System.out.println("El no va a digievolucionar :C   ");
    }

    @Override
    public void levelUp() {

    }
}

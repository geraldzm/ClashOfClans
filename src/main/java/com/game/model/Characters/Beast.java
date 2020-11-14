package com.game.model.Characters;

import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Team;

public class Beast extends Distance {
    public Beast(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, ID.BEAST, imgPath, team, gameBoard, handlerGameObjects);
        strokePerTime = 3; // para pruebas
    }

    @Override
    public void upgrade() {
        System.out.println("El no va a digievolucionar :C   ");
    }
}

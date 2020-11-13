package com.game.model.Characters;

import com.game.model.Defense;
import com.game.model.GameBoard;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;
import java.util.ArrayList;

public class AirDefense extends Defense {
    
    public AirDefense(int x, int y, String imgPath, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, imgPath, gameBoard, handlerGameObjects);
    }
    
    @Override
    public boolean isSomeoneInRange() {
        boolean isTargetOk = target != null && !target.isDead();
        
        if(isTargetOk && isInRange(target.getLocation())) return true;
            
        ArrayList<Warrior> warriors = gameBoard.getWarriorsById(ID.AIR);

        if(warriors == null || warriors.isEmpty())return false;

        for (int i = 0; i < warriors.size(); i++) { // buscamos el mas cercano
            if(isInRange(warriors.get(i).getLocation())){
                target = warriors.get(i);
                return true;
            }
        }
        
        target = null; // no hab[ia nadie en el rango
        return false;
    }
}

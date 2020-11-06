package com.game.model;

import java.awt.event.TextEvent;

public enum Team {
    ENEMY, FRIEND, DEFENSE;


    public Team getEnemy(){
        if(this == Team.FRIEND){
            return Team.ENEMY;
        }else if(this == Team.ENEMY){
            return Team.FRIEND;
        }
        return null;
    }


}

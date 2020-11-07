package com.game.model.Characters;

import com.game.model.*;

import java.awt.*;

public class Barbarian extends Warrior {


    public Barbarian(int x, int y, Team team, GameBoard gameBoard) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard);
        setImage(Tools.getIcon.apply("Barbarian.png")
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }

    @Override
    public void upgrade() {
        System.out.println("Incrementando ataque a 10000");
    }

    @Override
    public void tick() {
        if(movement == 60){
            move();
            movement = 0;
        }else{
            movement++;
        }
    }

    @Override
    public void move() {
        int xv = Math.random() > 0.5 ? -1: 1;
        int yv = Math.random() > 0.5 ? -1: 1;

        Point p = new Point(getLocation().x + xv, getLocation().y + yv);

        if(!gameBoard.isPositionOccupied(p)){
            gameBoard.moveCharacter(getLocation(), p);
            setLocation(p);
        }
    }
}

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
        if (target == null || !target.getLocation().equals(targetLocation))
            move();
        else if(target.getLocation().distance(getLocation()) <= range)
            attack();
    }

    @Override
    public void move() {}
}

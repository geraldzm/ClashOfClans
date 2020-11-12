package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import java.awt.*;

public class ContactWarrior extends Warrior{

    private static String warriorSound = "barbarian_hit_stuff.wav";
    
    public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard, handlerGameObjects);
        range = 1;
        strokePerTime = 2;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        super.displayHealthBar(g);
    }
    
    @Override
    public void upgrade() {
        System.out.println("Incrementando ataque a 10000");
    }

    private static void makeSound(){
        Tools.playSound(warriorSound);
    }

    @Override
    public void hit(int damage) { // la razon de esta redundancia de metodos es que si se quiere poner recistencia de ataque por ejemplo, eso se har[ia aqui en hit
        makeSound();
        reduceHealth(damage);
    }

    @Override
    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }

    @Override
    public Point heuristic() {
        return null;
    }
}

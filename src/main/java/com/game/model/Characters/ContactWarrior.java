package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ContactWarrior extends Warrior{

    private static String warriorSound = "barbarian_hit_stuff.wav";
    
    /*public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, ID.CONTACT, team, gameBoard, handlerGameObjects);
        range = 1;
        strokePerTime = 5;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }*/

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * @param images debe haber al menos 1 imagen, la primera imagen es por defecto
     * */
    public ContactWarrior(int maxHealth, int troops, int appearanceLevel, int range, int strokePerTime, int speed, ImageIcon[] images) {
        super(ID.CONTACT, maxHealth, troops, appearanceLevel, range, strokePerTime, speed, images);
    }

    /**
     * <h1>para clonar</h1>
     * */
    public ContactWarrior(Warrior contactWarrior, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(contactWarrior, gameBoard, handlerGameObjects);
    }

    @Override
    public void upgrade(int level) {
        System.out.println("Incrementando ataque a 10000");
    }

    @Override
    public void levelUp() {
        // cada vez que aumente de nivel
    }

    @Override
    public void makeSound() {
        Tools.playSound(warriorSound);
    }

    @Override
    public void hit(int damage) { // la razon de esta redundancia de metodos es que si se quiere poner recistencia de ataque por ejemplo, eso se har[ia aqui en hit
        reduceHealth(damage);
    }


    @Override
    public Point heuristic() {
        return null;
    }

    @Override
    public Warrior clone(Warrior w) {
        return new ContactWarrior(w, w.getGameBoard(), w.getHandlerGameObjects());
    }


}
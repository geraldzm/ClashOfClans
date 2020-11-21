package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static com.game.model.Tools.limit;

public class ContactWarrior extends Warrior{

    private static String warriorSound = "barbarian_hit_stuff.wav";

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * @param images debe haber al menos 1 imagen, la primera imagen es por defecto
     * */
    public ContactWarrior(int maxHealth, String name, int troops, int appearanceLevel, int range, int strokePerTime, int speed, ImageIcon[] images) {
        super(ID.CONTACT, name, maxHealth, troops, appearanceLevel, range, strokePerTime, speed, images);
    }

    /**
     * <h1>para clonar</h1>
     * */
    public ContactWarrior(Warrior contactWarrior, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(contactWarrior, gameBoard, handlerGameObjects);
    }

    @Override
    public void upgrade(int level) {
        //nivel maximo = 5
        //gana por nivel :
        // 20 de vida
        setHealth(limit(getHealth() + level*20, getHealth()*20*5, 1));
        // 1 de golpe
        this.strokePerTime =  limit(this.strokePerTime + level, this.strokePerTime+5, 1);

        // 1 de rapidez
        if(getSeep() <= 10 ) return;
        setSeep(limit(getSeep() - level, 10000, 10));
    }

    @Override
    public void levelUp() {
        // cada vez que aumente de nivel
        System.out.println("Se aumenta: " + getLevel());
        if(getLevel() > 5) return;
        setHealth(getHealth() + 20);
        // 1 de golpe
        this.strokePerTime++;
        // 1 de rapidez
        setSeep(getSeep() -1);
        System.out.println(this);
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
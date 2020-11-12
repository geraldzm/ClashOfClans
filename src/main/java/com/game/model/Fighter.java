package com.game.model;

import com.game.model.Handles.HandlerGameObjects;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * <h1>Todos los personajes que pueden atacar</h1>
 * <p>Es el que contiene las igualdades entre los soldados y las Defensas</p>
 * */
public abstract class Fighter extends Character {

    protected int range; // rango en el que puede atacar (en cuadros de matriz lógica)
    protected int strokePerTime; // cuantos golpes pega por segundo (osea va golpear por frame: strokePerTime/60)
    protected long cooldown = 1000; // Constante de tiempo en milisegundos del cooldown
    protected Date timer; // Fecha que usa para calcular la diferencia de milisegundos
    private int level;
    
    protected Warrior target; // cosa a la que estamos ataconado/yendo
    protected GameBoard gameBoard;
    protected HandlerGameObjects handlerGameObjects;

    protected static Random random = new Random();
    
    public abstract void upgrade(); // aumenta de nivel, mejora da;o, rango, etc

    public Fighter(double x, double y, int width, int height, ID id, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, width, height, id, team);
        this.gameBoard = gameBoard;
        this.handlerGameObjects = handlerGameObjects;
        
        timer = new Date();
    }

    public Fighter(int x, int y, int width, int height, ID id, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, width, height, id, team);
        this.gameBoard = gameBoard;
        this.handlerGameObjects = handlerGameObjects;
        
        timer = new Date();
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setTarget(Warrior target) {
        this.target = target;
    }
   
    /**
     * <h1>Ataque</h1>
     * <p>En este metodo se encapsula a quíen atacar y todo lo que conlleva</p>
     * */
    public void attack(){
        if(target == null) System.out.println("wtf");
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);
           // System.out.println("de: " +getLocation());

            timer = new Date();
        }
    }

    public boolean isSomeoneInRange() {
        if(target != null && !target.isDead() && isInRange(target.getLocation())) return true;

        ArrayList<Warrior> warriors = switch (getTeam()) {
            case FRIEND -> gameBoard.getEnemies();
            case ENEMY -> gameBoard.getFriends();
            default -> random.nextInt(100) >= 50 ? gameBoard.getEnemies() : gameBoard.getFriends();
        };

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

    // retorna un random de la lista, si esta vacia entonces null
    private Warrior getRandom(ArrayList<Warrior> warriors){
        if(warriors.isEmpty()) return null; // no hay nadie
        return warriors.get(random.nextInt(warriors.size())); // sacamos un enemigo random
    }


    /**
     * <h1>Si esta en rango para atacar</h1>
     * */
    public boolean isInRange(Point p) {
        return Math.abs(p.x - location.x) <= range && Math.abs(p.y - location.y) <= range ;
    }
}

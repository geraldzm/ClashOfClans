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
    
    protected boolean canAttack = true; //para medir los tiempos de cada cuanto puede atacar
    
    private int level;
    
    protected Warrior target; // cosa a la que estamos ataconado/yendo
    protected Point targetLocation; // la locacion del target cuando lo apuntamos
    protected GameBoard gameBoard;
    protected HandlerGameObjects handlerGameObjects;
    
    private static Random random = new Random();
    
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
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);
            canAttack = false;
            
            timer = new Date();
        }
    }

    public void findTarget() {
        ArrayList<Warrior> area = gameBoard.getArea(range, new Point(getLocation().x, getLocation().y), getTeam());

        if(!area.isEmpty()){
            target = area.get(0);
            return;
        }

        // seguimos si no había nadie en el area

        switch (getTeam()){
            case ENEMY -> target = getRandom(gameBoard.getFriends());
            case FRIEND -> target = getRandom(gameBoard.getEnemies());
            case DEFENSE -> { // para ser justos y no atacar solo a un team, tiramos un random para saber a cual atacamos
                if(random.nextInt(100) >= 50) {
                    target = getRandom(gameBoard.getFriends());
                    if(target != null) return; // si no encontro ninguno vamos a intentar con el otro equipo
                }
                target = getRandom(gameBoard.getFriends());
            }
        }

    }

    // retorna un random de la lista, si esta vacia entonces null
    private Warrior getRandom(ArrayList<Warrior> warriors){
        if(warriors.isEmpty()) return null; // no hay nadie
        return warriors.get(random.nextInt(warriors.size())); // sacamos un enemigo random
    }
}

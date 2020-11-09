package com.game.model;

import java.awt.*;

/**
 * <h1>Todos los personajes que pueden atacar</h1>
 * <p>Es el que contiene las igualdades entre los soldados y las Defensas</p>
 * */
public abstract class Fighter extends Character {

    protected int range; // rango en el que puede atacar (en cuadros de matriz lógica)
    protected int strokePerTime; // cuantos golpes pega por segundo (osea va golpear por frame: strokePerTime/60)
    private int level;
    protected Warrior target; // cosa a la que estamos ataconado/yendo
    protected Point targetLocation; // la locacion del target cuando lo apuntamos
    protected GameBoard gameBoard;

    public abstract void upgrade(); // aumenta de nivel, mejora da;o, rango, etc

    public Fighter(double x, double y, int width, int height, ID id, Team team, GameBoard gameBoard) {
        super(x, y, width, height, id, team);
        this.gameBoard = gameBoard;
    }

    public Fighter(int x, int y, int width, int height, ID id, Team team, GameBoard gameBoard) {
        super(x, y, width, height, id, team);
        this.gameBoard = gameBoard;
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
       // System.out.println("Atacando a"+target.getName()+" del equipo "+target.getTeam());
        target.hit(strokePerTime);
    }

    public void findTarget() {

        Character[][] area = gameBoard.getArea(range, new Point(getLocation().x, getLocation().y), getTeam());

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] != null) {
                    setTarget((Warrior) area[i][j]);
                    targetLocation = target.getLocation();
                    return;
                }
            }
        }

        for (int i = 0; i < gameBoard.getWidth(); i++){
            for (int j = 0; j < gameBoard.getHeight(); j++){
                if (getLocation().x == i && getLocation().y == j) continue;
                if (gameBoard.getHittableObjects()[i][j] != null) {
                    System.out.println("Se envia: " +i +" "+ j);
                    setTarget((Warrior) gameBoard.getObjectsInGame()[i][j]);
                    targetLocation = target.getLocation();
                    return;
                }
            }
        }
    }
}

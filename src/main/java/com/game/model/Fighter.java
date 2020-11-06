package com.game.model;

import java.awt.*;

/**
 * <h1>Todos los personajes que pueden atacar</h1>
 * <p>Es el que contiene las igualdades entre los soldados y las Defensas</p>
 * */
public abstract class Fighter extends Character{

    protected int range; // rango en el que puede atacar (en cuadros de matriz lógica)
    private int strokePerTime; // cuantos golpes pega por segundo (osea va golpear por frame: strokePerTime/60)
    private int level;
    protected Fighter target; // cosa a la que estamos ataconado/yendo
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

    public void setTarget(Fighter target) {
        this.target = target;
    }

}

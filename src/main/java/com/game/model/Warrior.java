package com.game.model;


import com.game.model.Interfaces.IMoveable;

/**
 * <h1>Todos los personajes que van a estar en un bando</h1>
 * */
public abstract class Warrior extends Fighter implements IMoveable {

    private int health;
    private int troops;
    private int appearanceLevel;

    public Warrior(double x, double y, int width, int height, ID id, Team team, GameBoard gameBoard) {
        super(x, y, width, height, id, team, gameBoard);
    }

    public Warrior(int x, int y, int width, int height, ID id, Team team, GameBoard gameBoard) {
        super(x, y, width, height, id, team, gameBoard);
    }

    /**
     * <h1>Ataque</h1>
     * <p>En este metodo se encapsula a quíen atacar y todo lo que conlleva</p>
     * */
    public void attack(){
   }



}

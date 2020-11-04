package com.game.model;


import com.game.model.Interfaces.IMoveable;

public abstract class Warriror extends Character implements IMoveable {

    private int range; // rango en el que puede atacar (en cuadros de matriz lógica)
    private int health;
    private int strokePerTime; // cuantos golpes pega por segundo (osea va golpear por frame: strokePerTime/60)
    private int level;
    private int troops;
    private int appearanceLevel;

    public Warriror(double x, double y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    /**
     * <h1>Ataque</h1>
     * <p>En este metodo se encapsula a quíen atacar y todo lo que conlleva</p>
     * */
    public void attack(){
        //ataca a todos, aun no la puedo programar ya que fata tener otras cosas primero

        // si no encuentra a nadie entonces:
        move(); // buscar[a  a quien atacar y se comienza a mover para ah[i
    }



}

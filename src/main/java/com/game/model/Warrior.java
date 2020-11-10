package com.game.model;


import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.IMoveable;
import com.game.utils.Node;

import java.awt.*;

/**
 * <h1>Todos los personajes que van a estar en un bando</h1>
 * */
public abstract class Warrior extends Fighter implements IMoveable{

    protected int health;
    private int troops;
    private int appearanceLevel;
    protected final int frames = 30;//cada 30 frames se mueve o intenta atacar
    protected int framesTimer=0;
    protected Node currentPath;

    public Warrior(double x, double y, int width, int height, ID id, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, width, height, id, team, gameBoard, handlerGameObjects);
        health = 100;
    }

    public Warrior(int x, int y, int width, int height, ID id, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, width, height, id, team, gameBoard, handlerGameObjects);
        health = 100;
    }


   protected void displayHealthBar(Graphics g){
        double x = hitBox.getX(), y = hitBox.getY();

        g.setColor(getTeam() == Team.FRIEND ? Color.cyan: Color.RED);
        // maximo de la barrra es 35
        int width = health*35/100;
        g.fillRoundRect((int)(x+2), (int)y, width, 5, 1,1);
   }

   public final void reduceHealth(int damage){
        health -= damage;
        if(health <= 0){
            die();
            health = 0;
        }
   }

   public boolean isDead(){
        return health <=0;
   }

    public abstract void hit(int damage);

    public abstract void die();

    public abstract Node heuristic();

}

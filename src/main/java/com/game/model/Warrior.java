package com.game.model;


import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.IMoveable;

import java.awt.*;

/**
 * <h1>Todos los personajes que van a estar en un bando</h1>
 * */
public abstract class Warrior extends Fighter implements IMoveable{

    protected int health;
    private int troops;
    private int appearanceLevel;
    protected final int frames = 10;//cada 30 frames se mueve o intenta atacar
    protected int framesTimer=0;
    protected Point nextMove;

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

    public void hit(int damage) {
        reduceHealth(damage);
    }

    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }

    public abstract Point heuristic();

    // retorna random -1,0,1
    public int getMovement(){
        double a = random.nextInt(100);
        if(a < 100/3) return -1;
        if(a >= 100/3 && a < (200/3)) return 1;
        return 0;
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        displayHealthBar(g);
    }

    @Override
    public void tick() {

        if(framesTimer == frames){

            if(isSomeoneInRange())attack();
            else move();

            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }

    @Override
    public void move() {
        int tries = 8;

        while (tries-- >0) {
            int x = getMovement();
            int y = getMovement();
            nextMove = new Point(x + location.x, y + location.y);

            if (!gameBoard.isPositionOccupied(nextMove)) {
                gameBoard.moveCharacter(getLocation(), nextMove);
                setLocation(nextMove);
                return;
            }
        }
    }


}
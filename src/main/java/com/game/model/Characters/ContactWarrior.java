package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.utils.*;

import java.awt.*;

public class ContactWarrior extends Warrior{

    public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard, handlerGameObjects);
        range = 1;
        strokePerTime = 2;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        super.displayHealthBar(g);
    }
    
    @Override
    public void upgrade() {
        System.out.println("Incrementando ataque a 10000");
    }
    
    @Override
    public void tick() {

        if(framesTimer == frames){

            if(target == null || target.isDead())findTarget();
            if(target == null)return; // no hay nadie

            if (target.getLocation().distance(getLocation()) > range) move();
            else attack();

            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }

    @Override
    public void move() {
        // tiene target

        if (targetLocation != target.getLocation() || currentPath == null) heuristic();

        if(currentPath == null)return;

        Point p = currentPath.toPoint();

        if(!gameBoard.isPositionOccupied(p)){
            gameBoard.moveCharacter(getLocation(), p);
            setLocation(p);

            currentPath = currentPath.getChild();
        }else{
            heuristic();
            System.out.println("Ocupada por" + getLocation() + " a "+ p);
            System.out.println("Hay: " + gameBoard.getObjectsInGame()[p.y][p.x]);
        }
    }


    @Override
    public void hit(int damage) { // la razon de esta redundancia de metodos es que si se quiere poner recistencia de ataque por ejemplo, eso se har[ia aqui en hit
        reduceHealth(damage);
        if(isDead())die();
    }

    @Override
    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }

    @Override
    public Node heuristic() {
        currentPath = ShortestPath.
                getPath(gameBoard.getObjectsInGame(), getLocation(), target.getLocation(), range);

        if(currentPath == null) return null; // si no hay camino

        targetLocation = target.getLocation();
        currentPath = currentPath.getChild();

        return currentPath;
    }
}

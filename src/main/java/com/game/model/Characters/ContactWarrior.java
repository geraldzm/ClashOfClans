package com.game.model.Characters;

import com.game.model.*;
import com.game.utils.*;

import java.awt.*;
import java.util.ArrayList;

public class ContactWarrior extends Warrior{

    public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard);
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

            if(target == null)findTarget();
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

        Point p = currentPath.get(0).toPoint();

        if(!gameBoard.isPositionOccupied(p)){
            gameBoard.moveCharacter(getLocation(), p);
            setLocation(p);

            currentPath.remove(0);
        }else
            heuristic();

    }


    @Override
    public void hit(int damage) { // la razon de esta redundancia de metodos es que si se quiere poner recistencia de ataque por ejemplo, eso se har[ia aqui en hit
        reduceHealth(damage);
    }

    @Override
    public void die() {
        System.out.println("Morido");
    }

    @Override
    public ArrayList<Node> heuristic() {
        currentPath = ShortestPath.
                getShortestPath(gameBoard.getObjectsInGame(), getLocation(), target.getLocation());

        targetLocation = target.getLocation();
        currentPath.remove(0);

        System.out.println(getLocation());

        return currentPath;
    }
}

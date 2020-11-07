package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Character;
import com.game.utils.*;

import java.awt.*;
import java.util.ArrayList;

public class ContactWarrior extends Warrior{
    private ArrayList<Node> currentPath;
    
    public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard);
        range = 1;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }
    

    @Override
    public void render(Graphics g) {
        super.render(g);
        super.displayHelthBar(g);
    }
    
    @Override
    public void upgrade() {
        System.out.println("Incrementando ataque a 10000");
    }
    
    @Override
    public void tick() {
        // Esto lo puse para probar que el path sirve
        // Es solo para test xD
        if(movement == 60){
            if (target == null)
                findTarget();
            else if (target.getLocation().distance(getLocation()) <= range)
                attack();
            else
                move();
            
            movement = 0;
        }else{
            movement++;
        }
    }

    @Override
    public void move() {
        // Esto lo puse para probar que el path sirve
        // Es solo para test xD
        // Lo que le preguntaba era donde ubicar la heuristica?
        // De momento el algoritmo de Shortest path esta en Utils con lo del Nodo
        if (targetLocation != target.getLocation() || currentPath == null){
            currentPath = new ShortestPath().
                     getShortestPath(gameBoard.getObjectsInGame(), getLocation(), target.getLocation());
                
            targetLocation = target.getLocation();
            currentPath.remove(0);
            
        } else if (currentPath != null){
            Point p = currentPath.get(0).toPoint();
            
            if(!gameBoard.isPositionOccupied(p)){
                gameBoard.moveCharacter(getLocation(), p);
                setLocation(p);
                
                currentPath.remove(0);
            }else{
                currentPath = new ShortestPath().
                         getShortestPath(gameBoard.getObjectsInGame(), getLocation(), target.getLocation());

                targetLocation = target.getLocation();
            }
        }
    }

    @Override
    public void findTarget() {
        Character[][] area = gameBoard.getArea(range, new Point(getLocation().x, getLocation().y), getTeam());
        
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (area[i][j] != null) {
                    setTarget((Fighter) area[i][j]);
                    targetLocation = target.getLocation();
                    return;
                }
            }
        }
        
        for (int i = 0; i < gameBoard.getWidth(); i++){
            for (int j = 0; j < gameBoard.getHeight(); j++){
                if (getLocation().x == i && getLocation().y == j) continue;
                
                if (gameBoard.getHittableObjects()[i][j] != null) {
                    setTarget((Fighter) gameBoard.getObjectsInGame()[i][j]);
                    targetLocation = target.getLocation();
                    return;
                }
            }
        }
    }

    @Override
    public void hit(int damage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

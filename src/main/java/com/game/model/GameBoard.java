package com.game.model;

import com.game.model.Interfaces.IHittable;

import java.awt.*;
import java.util.ArrayList;

/**
 * <h1>Tablero de juego</h1>
 * <p>Esta clase contiene toda la <strong>lógica</strong> del escenario de juego</p>
 * */
public class GameBoard {

    private final int width, height;
    private Character[][] objectsInGame; // todos los objetos que tienen un campo en la matriz
    private IHittable[][] hittableObjects; // todos los objetos que pueden ser golpeados

    /**
     * Dimensiones de la matriz
     * */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        objectsInGame = new Character[this.width][this.height];
        hittableObjects = new IHittable[this.width][this.height];
    }

    public IHittable[][] getArea(int range, Point from, Team team){
        return null;
    }

    public IHittable[][] getArea(int range, Point from, ID id){
        return null;
    }

    public IHittable[][] getArea(int range, Point from, Team team, ID id){
        return null;
    }

    /**
     * <h1></h1>
     * <p>Retorna un area de la matriz a partir de un punto y un rango</p>
     * @return un array
     * */
    public IHittable[][] getArea(int range, Point from){
        Point limits = new Point();
        
        from.x -= range;
        from.y -= range;
        
        from = setRestrictions(from);
        
        limits.x = from.x + 2*range;
        limits.y = from.y + 2*range;
        
        limits = setRestrictions(limits);
        
        IHittable[][] area = new IHittable[limits.y - from.y + 1][limits.x - from.x + 1];
           
        for(int i = from.y; i < limits.y; i++){
            for(int j = from.x; j < limits.x; j++){
                area[i -from.y][j - from.x] = hittableObjects[i][j];
            }
        }
        
        return area;
    }
    
    /**
     * <h1></h1>
     * <p>Retorna el punto restandole el rango. Respeta los limites de 
     * la matriz</p>
     * @return un punto
     * */
    private Point setRestrictions(Point point){
        if (point.x < 0) point.x = 0;
        if (point.y < 0) point.y = 0;
        
        if (point.x >= width) point.x = width - 1;
        if (point.y >= height) point.y = height - 1;
        
        return point;
    }

    /**
     * @return true si esta ocupada
     * */
    public boolean isPositionOccupied(Point point){
        return objectsInGame[point.x][point.y] != null;
    }

    /**
     * <h1>Inserta un caracter en la matriz</h1>
     * <p>Se utiliza para agregar caracteres nuevos en el juego <br />
     *   si la clase es hitteable también la agrega a la matrix de hits
     *     <i>no valida si hay alguien ahí o no</i>
     * </p>
     * */
    synchronized public void addCharacter(Character character){
        objectsInGame[character.getLocation().x][character.getLocation().y] = character;
        if(character instanceof IHittable)
            hittableObjects[character.getLocation().x][character.getLocation().y] = (IHittable) character;
    }

    synchronized public void addCharacteres(ArrayList<Character> characteres){
        for (int i = 0; i < characteres.size(); i++)
            addCharacter(characteres.get(i));
    }

    /**
     * <h1>Borrar un caracter en la matriz</h1>
     * <p> si la clase es hitteable también lo elimina de la matrix de hits
     *     <i>no valida si hay alguien ahí o no</i>
     * </p>
     * */
    synchronized public void removeCharacter(Character character){
        objectsInGame[character.getLocation().x][character.getLocation().y] = null;
        if(character instanceof IHittable)
            hittableObjects[character.getLocation().x][character.getLocation().y] = null;
    }

    /**
     * <h1>Moverse en el mapa</h1>
     * <p>
     *     mueve lo que sea que este en matriz de caracteres <strong>y hittable</strong> <br />
     *     Estas dos nunca van a interferir una con la otra y Caracteres >= hittable
     *     <i>no valida lo que mueve, solo lo mueve</i>
     * </p>
     * */
    synchronized public void moveCharacter(Point from, Point to){
        objectsInGame[from.x][from.y] = objectsInGame[to.x][to.y];
        objectsInGame[from.x][from.y] = null;

        hittableObjects[from.x][from.y] = hittableObjects[to.x][to.y];
        hittableObjects[from.x][from.y] = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Character[][] getObjectsInGame() {
        return objectsInGame;
    }

    public void setObjectsInGame(Character[][] objectsInGame) {
        this.objectsInGame = objectsInGame;
    }

    public IHittable[][] getHittableObjects() {
        return hittableObjects;
    }

    public void setHittableObjects(IHittable[][] hittableObjects) {
        this.hittableObjects = hittableObjects;
    }
}

package com.game.model;

import static com.game.model.Fighter.random;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * <h1>Tablero de juego</h1>
 * <p>Esta clase contiene toda la <strong>lógica</strong> del escenario de juego</p>
 * */
public class GameBoard {

    private final int width, height;
    private Game game;
    private Character[][] objectsInGame; // todos los objetos que tienen un campo en la matriz
    private Warrior[][] hittableObjects; // todos los objetos que pueden ser golpeados
    private ArrayList<Warrior> friends, enemies;

    /**
     * Dimensiones de la matriz
     * */
    public GameBoard(Game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        objectsInGame = new Character[this.width][this.height];
        hittableObjects = new Warrior[this.width][this.height];
        friends = new ArrayList<>();
        enemies = new ArrayList<>();
    }


    /**
     * <h1></h1>
     * <p>Limita el area de donde buscar hittables</p>
     * */
    private void limitArea(Point from, Point limits, int range){
        from.x -= range;    
        from.y -= range;
        
        from = setRestrictions(from);
        
        limits.x = from.x + 2*range + 1;
        limits.y = from.y + 2*range + 1;
        
        limits = setRestrictions(limits);
    }
    
    /**
     * <h1></h1>
     * <p>Retorna el punto ajustando los limites de 
     * la matriz</p>
     * @return un punto
     * */
    public Point setRestrictions(Point point){
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
        return isPointOutOFBoard(point) || objectsInGame[point.y][point.x] != null;
    }

    public boolean isPointOutOFBoard(Point point){
        return point.x < 0 || point.y < 0 || point.x >= objectsInGame.length || point.y >= objectsInGame.length;
    }

    /**
     * <h1>Inserta un caracter en la matriz</h1>
     * <p>Se utiliza para agregar caracteres nuevos en el juego <br />
     *   si la clase es hitteable también la agrega a la matrix de hits
     *     <i>no valida si hay alguien ahí o no</i>
     * </p>
     * */
    synchronized public void addCharacter(Character character){
        objectsInGame[character.getLocation().y][character.getLocation().x] = character;

        if(character instanceof Warrior){
            hittableObjects[character.getLocation().y][character.getLocation().x] = (Warrior) character;

            switch (character.getTeam()){
                case ENEMY -> enemies.add((Warrior)character);
                case FRIEND -> friends.add((Warrior)character);
            }

        }

    }

    synchronized public <T extends Character> void addCharacteres(ArrayList<T> characteres){
        System.out.println("Se agregan: " + characteres.size());
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
        objectsInGame[character.getLocation().y][character.getLocation().x] = null;
        if(character instanceof Warrior){
            hittableObjects[character.getLocation().y][character.getLocation().x] = null;

            switch (character.getTeam()){
                case ENEMY -> enemies.remove(character);
                case FRIEND -> friends.remove(character);
            }
        }
    }

    public void checkWinner(){
        if(enemies.isEmpty()) game.winner(Team.FRIEND);
        if(friends.isEmpty()) game.winner(Team.ENEMY);
    }

    /**
     * <h1>Moverse en el mapa</h1>
     * <p>
     *     mueve lo que sea que este en matriz de caracteres <strong>y hittable</strong> <br />
     *     Estas dos nunca van a interferir una con la otra y Caracteres >= hittable
     *     <i>no valida lo que mueve, solo lo mueve</i>
     * </p>
     * */
    synchronized public boolean moveCharacter(Point from, Point to){
        if(isPositionOccupied(to)) return false;
        
        if(objectsInGame[from.y][from.x] == null){
            return false;
        }
        if(objectsInGame[to.y][to.x] != null){
            System.out.println(" ");
        }
         objectsInGame[to.y][to.x] = objectsInGame[from.y][from.x];
         objectsInGame[from.y][from.x] = null;

         hittableObjects[to.y][to.x] = hittableObjects[from.y][from.x];
         hittableObjects[from.y][from.x] = null;

         return true;
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

    public Warrior[][] getHittableObjects() {
        return hittableObjects;
    }

    public void setHittableObjects(Warrior[][] hittableObjects) {
        this.hittableObjects = hittableObjects;
    }

    public ArrayList<Warrior> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Warrior> friends) {
        this.friends = friends;
    }

    public ArrayList<Warrior> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Warrior> enemies) {
        this.enemies = enemies;
    }

    // Nota: Para mas eficiencia corro ambas listas simultaneamente
    public ArrayList<Warrior> getWarriors(){
        ArrayList<Warrior> list = new ArrayList<>();
        int length = enemies.size() > friends.size() ? enemies.size() : friends.size();
        
        for (int i = 0; i < length; i++){
            
            if (i < enemies.size()){
                list.add(enemies.get(i));
            }
            
            if (i < friends.size()){
                list.add(friends.get(i));
            }
        }
        
        return list;
    }

    public ArrayList<Warrior> getWarriorsById(ID id){
        ArrayList<Warrior> list = new ArrayList<>();
        int length = enemies.size() > friends.size() ? enemies.size() : friends.size();
        
        for (int i = 0; i < length; i++){
            if (i < enemies.size()&& enemies.get(i).getId() == id){
                list.add(enemies.get(i));
            }
            
            if (i < friends.size() && friends.get(i).getId() == id){
                list.add(friends.get(i));
            }
        }
        
        return list;
    }
    
    public ArrayList<Warrior> getWarriorsNotById(ID id){
        ArrayList<Warrior> list = new ArrayList<>();
        int length = enemies.size() > friends.size() ? enemies.size() : friends.size();
        
        for (int i = 0; i < length; i++){
            if (i < enemies.size() && enemies.get(i).getId() != id){
                list.add(enemies.get(i));
            }
            
            if (i < friends.size() && friends.get(i).getId() != id){
                list.add(friends.get(i));
            }
        }
        
        return list;
    }

    public ArrayList<Warrior> getTeam(Team team){
        ArrayList<Warrior> warriors;
        switch (team) {
            case FRIEND -> warriors = getEnemies();
            case ENEMY -> warriors = getFriends();
            default -> {
                warriors = new ArrayList<>();
                warriors.addAll(getEnemies());
                warriors.addAll(getFriends());
            }
        }
        return warriors;
    }

    public Point getEmptyRandomPosition(){
        Point p;
        Random r = new Random();
        do{
            p = new Point(r.nextInt(20), r.nextInt(20));
        }while (isPositionOccupied(p));
        return p;
    }
}

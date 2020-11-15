package com.game.model;

import com.game.model.Handles.HandlerGameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h1>Todos los personajes que pueden atacar</h1>
 * <p>Es el que contiene las igualdades entre los soldados y las Defensas</p>
 * */
public abstract class Fighter extends Character {

    protected int range; // rango en el que puede atacar (en cuadros de matriz lógica)
    protected int strokePerTime; // cuantos golpes pega por segundo (osea va golpear por frame: strokePerTime/60)

    protected long cooldown = 1000; // Constante de tiempo en milisegundos del cooldown
    protected Date timer; // Fecha que usa para calcular la diferencia de milisegundos

    private int level; // nivel del personaje
    protected int appearanceLevel = 0; // nivel del personaje

    protected int frames = 30;//cada 30 frames se mueve o intenta atacar
    protected int framesTimer=0;

    protected transient Warrior target; // cosa a la que estamos ataconado/yendo
    protected transient GameBoard gameBoard;
    protected transient HandlerGameObjects handlerGameObjects;

    private transient Function <Warrior, Boolean> targetCriteria; // critero por el cual se selecciona el target

    protected transient static Random random = new Random();

    /**
     * <h1>para clonar</h1>
     * */
    public Fighter(Fighter fighter, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(fighter);
        this.range = fighter.range;
        this.strokePerTime = fighter.strokePerTime;
        this.level = fighter.level;
        this.appearanceLevel = fighter.appearanceLevel;
        this.frames = fighter.frames;
        this.framesTimer = fighter.framesTimer;

        timer = new Date();
        this.gameBoard = gameBoard;
        this.handlerGameObjects = handlerGameObjects;
        targetCriteria = warrior -> isInRange(warrior.getLocation());
    }


    public Fighter() {}

    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * */
    public Fighter(ID id, Team team, int range, int strokePerTime, int level, int appearanceLevel,  int speed, ImageIcon[] images) {
        super(id, team);
        this.gameBoard = null;
        this.handlerGameObjects = null;
        this.range = range;
        this.strokePerTime = strokePerTime;
        this.appearanceLevel = appearanceLevel;
        this.level = level;
        upgrade(level);
        this.setImages(images);
        setSeep(speed);

        timer = new Date();
        targetCriteria = warrior -> isInRange(warrior.getLocation());

    }

    /**
     * <h1>Constructor que creemos la defensa</h1>
     * */
    public Fighter(int x, int y, String name, ID id, Team team, int range, int strokePerTime, int level, int appearanceLevel, String imagePath, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, name, id, team);
        this.gameBoard = gameBoard;
        this.handlerGameObjects = handlerGameObjects;
        this.range = range;
        this.strokePerTime = strokePerTime;
        this.appearanceLevel = appearanceLevel;
        setImageByPath(imagePath);
        timer = new Date();
        upgrade(level);
        targetCriteria = warrior -> isInRange(warrior.getLocation());

    }

    public abstract void upgrade(int level); // cuando vamos a jugar un nivel los personajes tienen stacks predeterminados

    public abstract void levelUp(); // Sube de nivel en base al nivel actual

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setHandlerGameObjects(HandlerGameObjects handlerGameObjects) {
        this.handlerGameObjects = handlerGameObjects;
    }

    @Override
    public void tick() {

        if(framesTimer == frames){
            if(isSomeoneInRange()) attack();
            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }

    /**
     * <h1>Ataque</h1>
     * <p>En este metodo se encapsula a quíen atacar y todo lo que conlleva</p>
     * */
    public void attack(){
        if(target == null) System.out.println("wtf");
        if (new Date().getTime() - timer.getTime() >= cooldown){
            target.hit(strokePerTime);
            timer = new Date();
            makeSound();
        }
    }

    public boolean isSomeoneInRange() {
        if(target != null && !target.isDead() && isInRange(target.getLocation())) return true;

        ArrayList<Warrior> warriors = gameBoard.getTeam(getTeam());

        if(warriors == null || warriors.isEmpty())return false;


        for (int i = 0; i < warriors.size(); i++) {// buscamos el mas cercano
            if (targetCriteria.apply(warriors.get(i))) {
                target = warriors.get(i);
                return true;
            }
        }

        target = null; // no hab[ia nadie en el rango
        return false;
    }

    /**
     * <h1>Si esta en rango para atacar</h1>
     * */
    public boolean isInRange(Point p) {
        return Math.abs(p.x - location.x) <= range && Math.abs(p.y - location.y) <= range ;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTargetCriteria(Function<Warrior, Boolean> targetCriteria) {
        this.targetCriteria = targetCriteria;
    }

    public Function<Warrior, Boolean> getTargetCriteria() {
        return targetCriteria;
    }

    public void setSeep(int frames) {
        this.frames = frames;
    }

    public abstract void makeSound();

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public HandlerGameObjects getHandlerGameObjects() {
        return handlerGameObjects;
    }
}

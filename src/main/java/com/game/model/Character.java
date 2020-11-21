package com.game.model;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Todos los personajes que tengan una posición en la matriz lógica</h1>
 * */
public abstract class Character extends GameObject{

    private String name;
    protected Point location; // en matriz lógica
    protected ImageIcon images[]; //atributo apariencia
    private Team team; // equipo al que pertenece

    public Character() {}

    /**
     * <h1>para clonar</h1>
     * */
    public Character(Character character) {
        super(character);
        this.name = character.name;
        if(location!= null) this.location = new Point(character.location.x, character.location.y);
        this.images = character.images.clone();
        this.team = character.team;
    }


    /**
     * <h1>Constructor para que el usuario cree sus personajes</h1>
     * */
    public Character(ID id, Team team, String name) {
        super(40, 40, id);
        this.location = null;
        this.team = team;
        this.name = name;
    }

    /**
     * <h1>Solo lo debe de usar la defensa</h1>
     * @param x x en la matriz logica
     * @param y y en la matriz logica
     * */
    public Character(int x, int y, String name, ID id, Team team) {
        super(x*40, y*40, 40, 40, id);
        this.location = new Point(x, y);
        this.team = team;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        super.setX(location.x*40);
        super.setY(location.y*40);
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon[] getImages() {
        return images;
    }

    public void setImages(ImageIcon[] images) {
        this.images = images;
        setImage(images[0].getImage());
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

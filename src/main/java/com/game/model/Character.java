package com.game.model;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * <h1>Todos los personajes que tengan una posición en la matriz lógica</h1>
 * */
public abstract class Character extends GameObject{

    private String name;
    protected Point location; // en matriz lógica
    private ImageIcon imges[]; //atributo apariencia
    private Team team; // equipo al que pertenece

    public Character(double x, double y, int width, int height, ID id, Team team) {
        super(x, y, width, height, id);
        this.team = team;
    }

    public Character(int width, int height, ID id) {
        super(width, height, id);
    }

    /**
     * @param x x en la matriz logica
     * @param y y en la matriz logica
     * */
    public Character(int x, int y, int width, int height, ID id, Team team) {
        super(x*40, y*40, width, height, id);
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

    public ImageIcon[] getImges() {
        return imges;
    }

    public void setImges(ImageIcon[] imges) {
        this.imges = imges;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

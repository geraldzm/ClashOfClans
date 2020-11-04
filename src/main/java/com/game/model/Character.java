package com.game.model;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * <h1>Todos los personajes que tengan una posición en la matriz lógica</h1>
 * */
public abstract class Character extends GameObject{

    private String name;
    private Point location; // en matriz lógica
    private ImageIcon imges[]; //atributo apariencia

    public Character(double x, double y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

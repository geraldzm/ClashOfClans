package com.game.model;

import com.game.model.Interfaces.IRenderable;
import com.game.model.Interfaces.ITick;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
* <h1>Clase papá de todos los objetos que estarán en pantalla</h1>
* */
public abstract class GameObject implements ITick, IRenderable, Serializable {

    protected ID id;
    protected double velX, velY;
    protected ImageIcon img;
    protected Rectangle2D hitBox;

    public GameObject(){}

    /**
     * <h1>para clonar</h1>
     * */
    public GameObject(GameObject gameObject){
        this.id = gameObject.id;
        this.velX = gameObject.velX;
        this.velY = gameObject.velY;
        this.img = new ImageIcon(gameObject.img.getImage());
        this.hitBox = new Rectangle2D.Double(gameObject.hitBox.getX(), gameObject.hitBox.getY(), gameObject.hitBox.getWidth(), gameObject.hitBox.getHeight());

    }

    /**
     * @param x en pixeles
     * @param y en pixeles
     * */
    public GameObject(double x, double y, int width, int height, ID id) {
        this.id = id;
        hitBox = new Rectangle2D.Double(x, y, width, height);
        img = new ImageIcon();
    }

    public GameObject(int width, int height, ID id) {
        this.id = id;
        hitBox = new Rectangle2D.Double(0, 0, width, height);
        img = new ImageIcon();
    }

    public abstract void tick();

    public void render(Graphics g){
        if(img != null){
            g.drawImage(img.getImage(), (int)hitBox.getX(), (int)hitBox.getY(), null);
        }
    }

    public boolean contains(Rectangle2D rectangle2D){
        return hitBox.contains(rectangle2D);
    }

    public boolean contains(Point point){
        return hitBox.contains(point);
    }

    public void setImage(Image img){
        this.img.setImage(img);
    }

    public void setImageByPath(String imgPath){
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance((int)hitBox.getWidth(),(int)hitBox.getHeight(), Image.SCALE_SMOOTH));
    }

    public double getX() {
        return hitBox.getX();
    }

    public void setX(float x) {
        hitBox.setRect(new Rectangle2D.Double(x, hitBox.getY(), hitBox.getWidth(), hitBox.getWidth()));
    }

    public double getY() {
        return hitBox.getY();
    }

    public void setY(float y) {
        hitBox.setRect(new Rectangle2D.Double(hitBox.getX(), y, hitBox.getWidth(), hitBox.getWidth()));
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}

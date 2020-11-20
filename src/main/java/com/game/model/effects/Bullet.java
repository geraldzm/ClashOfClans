package com.game.model.effects;

import com.game.model.GameObject;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

import java.awt.*;

public abstract class Bullet extends GameObject {


    protected Warrior target;
    protected double velBullet = -2.5; // default
    private int damage;
    private final HandlerGameObjects handlerGameObjects;

    public Bullet(double x, double y, int w, int h, ID id, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x+20, y+20, w, h, id);
        this.target = target;
        this.damage = damage;
        this.handlerGameObjects = handlerGameObjects;
        handlerGameObjects.addObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)getX(), (int)getY(), (int)getHitBox().getWidth(),(int)getHitBox().getHeight());
        //hitbox
        g.setColor(Color.RED);
        g.drawRect( (int)hitBox.getX(), (int)hitBox.getY(),  (int)hitBox.getWidth(), (int)hitBox.getHeight());
    }

    @Override
    public void tick() {
        if(this.hitBox.intersects(target.getHitBox()) || target.isDead()) generateDamage();
        else{
            // nos movemos
            double distance = Math.sqrt(Math.pow(hitBox.getX()-(target.getX()+20), 2) + Math.pow(hitBox.getY()-(target.getY()+20), 2));
            if(distance == 0) return;
            velX = (velBullet/distance)*(hitBox.getX()-(target.getX()+20));
            velY = (velBullet/distance)*(hitBox.getY()-(target.getY()+20));
            hitBox.setFrame(velX+getX(), velY+getY(), hitBox.getWidth(), hitBox.getHeight());
        }
    }

    public void generateDamage(){
        target.hit(damage);
        handlerGameObjects.removeObject(this); // nos eliminamos
    }

    public Warrior getTarget() {
        return target;
    }
}

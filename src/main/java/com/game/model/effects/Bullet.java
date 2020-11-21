package com.game.model.effects;

import com.game.model.GameObject;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

import java.awt.*;

public abstract class Bullet extends GameObject implements Runnable{


    protected Warrior target;
    protected double velBullet = -2.5; // default
    private int damage;
    private final HandlerGameObjects handlerGameObjects;
    private Thread thread;
    protected final Object lock;
    protected boolean alive;

    public Bullet(double x, double y, int w, int h, ID id, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x+20, y+20, w, h, id);
        this.target = target;
        this.damage = damage;
        this.handlerGameObjects = handlerGameObjects;
        handlerGameObjects.addObject(this);
        lock = new Object();
        thread = new Thread(this);
        thread.start();
        alive = true;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)getX(), (int)getY(), (int)getHitBox().getWidth(),(int)getHitBox().getHeight());
    }

    @Override
    public void tick() {
        if(this.hitBox.intersects(target.getHitBox()) || target.isDead()) generateDamage();
        else calculate(); // nos movemos
    }

    @Override
    public void run() {
        while (alive){
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double distance = Math.sqrt(Math.pow(hitBox.getX()-(target.getX()+20), 2) + Math.pow(hitBox.getY()-(target.getY()+20), 2));
                if(distance == 0) return;
                velX = (velBullet/distance)*(hitBox.getX()-(target.getX()+20));
                velY = (velBullet/distance)*(hitBox.getY()-(target.getY()+20));
                hitBox.setFrame(velX+getX(), velY+getY(), hitBox.getWidth(), hitBox.getHeight());
            }
        }
    }

    private void calculate(){
        synchronized (lock) {
            lock.notify();
        }
    }


    public void generateDamage(){
        alive = false;
        calculate();// para que muera el thread
        target.hit(damage);
        handlerGameObjects.removeObject(this); // nos eliminamos
    }

    public Warrior getTarget() {
        return target;
    }
}

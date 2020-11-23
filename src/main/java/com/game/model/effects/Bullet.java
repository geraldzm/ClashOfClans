package com.game.model.effects;

import com.game.model.GameObject;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Tools;
import com.game.model.Warrior;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bullet extends GameObject implements Runnable{


    protected Warrior target;
    protected double velBullet = -2.5, rotation; // default
    private int damage;
    private final HandlerGameObjects handlerGameObjects;
    private Thread thread;
    protected final Object lock;
    protected BufferedImage bfimg;
    protected boolean alive, impacting;
    private double directionDamage; // para cambiar la direccion del numero que indica el da;o en pantalla
    private double rotationx, rotationy; // ejes que se usan para rotar la imagen
    private Rectangle2D damagePosition;

    public Bullet(double x, double y, int w, int h, ID id, String img, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x+20, y+20, w, h, id);
        this.target = target;
        this.damage = damage;
        this.handlerGameObjects = handlerGameObjects;
        handlerGameObjects.addObject(this);
        lock = new Object();
        thread = new Thread(this);
        thread.start();
        alive = true;
        impacting = false;
        directionDamage = 0.5;

        Image image = Tools.getIcon.apply(img).getScaledInstance(w, h, Image.SCALE_SMOOTH);
        bfimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        bfimg.getGraphics().drawImage(image, 0, 0 , null);
        setImage(bfimg);

        rotationx = hitBox.getWidth()/2;
        rotationy = hitBox.getHeight()/2;
    }

    @Override
    public void render(Graphics g) {
        if(!impacting)super.render(g);
        else drawDamageMade(g); // dibujamos la cantidad de da;o realizado

    }

    protected void drawDamageMade(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("-"+damage, (int)damagePosition.getX(), (int)damagePosition.getY());
        damagePosition.setFrame(directionDamage+damagePosition.getX(), damagePosition.getY()-1, damagePosition.getWidth(), damagePosition.getHeight());
        directionDamage *= -1;
    }

    @Override
    public void tick() {
        if(this.hitBox.intersects(target.getHitBox()) || target.isDead() || !alive) impact();
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
                performCalculationsONThread();
            }
        }
    }

    /**
     * <h1>Lo que pasa aqui se calcula en un thread aparte</h1>
     * <p>Esto ocurre solo si el bullet no ha impactado</p>
     * */
    public void performCalculationsONThread(){
        calculateMovement();
        calculateRotation();
    }

    // calcula el movimiento
    protected void calculateMovement(){
        double distance = Math.sqrt(Math.pow(hitBox.getX()-(target.getX()+20), 2) + Math.pow(hitBox.getY()-(target.getY()+20), 2));
        if(distance == 0) return;
        velX = (velBullet/distance)*(hitBox.getX()-(target.getX()+20));
        velY = (velBullet/distance)*(hitBox.getY()-(target.getY()+20));
        hitBox.setFrame(velX+getX(), velY+getY(), hitBox.getWidth(), hitBox.getHeight());
    }

    private void calculate(){
        synchronized (lock) {
            lock.notify();
        }
    }

    // cuando el bullet impacta
    public void impact(){
        if(impacting) return; // si ya esta impactando entonces no hacemos nada
        if(!alive) handlerGameObjects.removeObject(this); // nos eliminamos

        target.hit(damage); // hacemos el dano
        damagePosition = new Rectangle2D.Double(getX(), getY(), 10, 10); // para que se dibuje el dano en la posicion que impactamo
        impacting = true;
        alive = false;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                impacting = false;
            }
        }, 500); // programamos que muera en 3s
    }

    // el defaul es girar el bullet
    protected void calculateRotation(){
        rotation += 0.2;
        if (rotation >= Math.PI*2) rotation = 0.2;
        if(rotation <= 0)return;
        AffineTransform tx = AffineTransform.getRotateInstance(rotation, rotationx, rotationy);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        setImage(op.filter(bfimg, null));
    }

    // settea el punto de la imagen del que se debe rotar
    public void setRotationPosition(double x, double y){
        this.rotationx = x;
        this.rotationy = y;
    }

    public Warrior getTarget() {
        return target;
    }
}

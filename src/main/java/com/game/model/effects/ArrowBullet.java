package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Tools;
import com.game.model.Warrior;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ArrowBullet extends Bullet{

    private BufferedImage bfimg;

    public ArrowBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 10, 5, ID.BULLET, target, damage, handlerGameObjects);

        int scaleX = 15;
        int scaleY = 30;
        Image image = Tools.getIcon.apply("Arrow_image.png").getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
        bfimg = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
        bfimg.getGraphics().drawImage(image, 0, 0 , null);
        setImage(bfimg);
    }


    public void render(Graphics g){
        if(img != null){
            g.drawImage(img.getImage(), (int)hitBox.getX(), (int)hitBox.getY(), null);
        }
    }

    // (Math.PI*3)/2 Derecha
    // Math.PI/2 Izquierda
    // Math.PI Arriba
    // 2 * Math.PI Abajo
    private double angulo(double x1, double y1, double x2, double y2){
        double ang = 0;

        // Enemigo: Arriba a la izquierda
        if (x1 > x2 && y1 > y2){
            ang = Math.PI;
            ang -= Math.atan(x1/y1);

        // Enemigo: Abajo a la izquierda
        } else if (x1 < x2 && y1 < y2){
            ang = 2 * Math.PI;
            ang -= Math.atan(x1/y1);

        // Enemigo: Arriba a la derecha
        } else if (x1 < x2 && y1 > y2){
            ang = Math.PI;
            ang += Math.atan(x1/y1);

        // Enemigo: Abajo a la derecha
        } else if (x1 > x2 && y1 < y2){
            ang = 2 * Math.PI;
            ang += Math.atan(x1/y1);
        }

        return ang;
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

       // Rotation information

        double rotationRequired = angulo(getX() + velX, getY() + velY, target.getX(), target.getY()); //Math.toRadians (45);
        System.out.println(velX + ", " + velY);
        double locationX = bfimg.getWidth() / 2;
        double locationY = bfimg.getHeight() / 2;

        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
       // g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

        setImage(op.filter(bfimg, null));
    }

}

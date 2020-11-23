package com.game.model.effects;

import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Warrior;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class ArrowBullet extends Bullet{

    public ArrowBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 15, 30, ID.BULLET, "Arrow_image.png", target, damage, handlerGameObjects);
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
    protected void calculateRotation(){
        // Rotation information
        double rotationRequired = angulo(velX, velY, target.getX(), target.getY());

        if (getY() > target.getY()) rotationRequired += Math.PI;

        if (rotationRequired <= 0)return;

        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 7.5, 15);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        setImage(op.filter(bfimg, null));
    }
}
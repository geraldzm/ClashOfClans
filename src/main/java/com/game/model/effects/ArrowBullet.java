package com.game.model.effects;

import com.game.Main;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Tools;
import com.game.model.Warrior;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ArrowBullet extends Bullet{

    private BufferedImage bfimg;
    private double rotation;

    public ArrowBullet(double x, double y, Warrior target, int damage, HandlerGameObjects handlerGameObjects) {
        super(x, y, 15, 30, ID.BULLET, target, damage, handlerGameObjects);
        int scaleX = 15;
        int scaleY = 30;
        Image image = Tools.getIcon.apply("Arrow_image.png").getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
        bfimg = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
        bfimg.getGraphics().drawImage(image, 0, 0 , null);
        rotation = 0;
        setImage(bfimg);
    }


    public void render(Graphics g){
        if(img != null){
            g.drawImage(img.getImage(), (int)hitBox.getX(), (int)hitBox.getY(), null);
        }
    }

    @Override
    public void tick() {
        super.tick();
        rotation += 0.2;
        if (rotation >= Math.PI*2) rotation = 0;
        AffineTransform tx = AffineTransform.getRotateInstance(rotation, 7.5, 15);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        setImage(op.filter(bfimg, null));
    }

}

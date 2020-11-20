package com.game.model.effects;

import com.game.model.GameObject;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;
import com.game.model.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Falta depurar
 * */
public class Explosion extends GameObject {

    private final int squareW = 220, squareH = 225; // explosion square dimensions
    private int frame = 0, index = 0, subx = 0;

    private boolean blowUp = false;
    private HandlerGameObjects handlerGameObjects;

    private static BufferedImage bfImage;
    static {
        bfImage = Tools.getIcon.apply("explotion.png");
    }

    public Explosion(double x, double y, ID id, HandlerGameObjects handlerGameObjects) {
        super(x+20, y+20, 50,50, id);
        this.handlerGameObjects = handlerGameObjects;
        blowUp = true;
        handlerGameObjects.addObject(this);
    }

    @Override
    public void render(Graphics g) {
        if(blowUp){
            g.drawImage(bfImage.getSubimage(subx, 0, squareW, squareH), (int)(getX()- squareW /2), (int)(getY() - squareH /2), null);
            if (frame++ > 2) {
                subx = index * squareW;
                index++;
                if(index > 6){
                    blowUp = false;
                    handlerGameObjects.removeObject(this);
                }
                frame = 0;
            }
        }
    }


    @Override
    public void tick() {}

}

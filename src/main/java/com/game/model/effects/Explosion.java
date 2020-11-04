package com.game.model.effects;

import com.game.model.GameObject;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.ID;

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
        try {
            bfImage = ImageIO.read(new File("src/explotion/image/explo1/explotion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Explosion(float x, float y, ID id, HandlerGameObjects handlerGameObjects) {
        super(x, y, 50,50, id);
        this.handlerGameObjects = handlerGameObjects;
        blowUp = true;
    }

    @Override
    public void render(Graphics g) {
        if(blowUp){
           // g.drawImage(bfImage.getSubimage(subx, 0, squareW, squareH), (int)(x- squareW /2), (int)(y - squareH /2), null);
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

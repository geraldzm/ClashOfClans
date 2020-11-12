package com.game.model;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Tools {

    // para sacar las imagenes
    public static Function<String, BufferedImage> getIcon = s -> {
        try {
            return ImageIO.read(new File("src/main/java/com/game/image/" + s));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de lectura de imagen: " + s);
        }
        return null;
    };
    
    // obtiene imagenes pero listas para componentes de Swing
    public static ImageIcon getComponentIcon(String path, int width, int height) throws FileNotFoundException, IOException{
        BufferedImage bg = ImageIO.read(new FileInputStream(path));

        Image dimg = bg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }
    
    // Reproduce un sonido
    public static synchronized void playSound(final String url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        new File("src/main/java/com/game/audio/" + url));
                clip.open(inputStream);
                clip.start(); 
            } catch (Exception e) {
                System.err.println("Audio no existe: " + e.getMessage());
            }
        }).start();
     }
}

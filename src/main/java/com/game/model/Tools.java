package com.game.model;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return;
       /* BRO WTF? WHY IS THIS STILL HERE? :))))))))))) 
        new Thread(() -> {
            try (AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new File("src/main/java/com/game/audio/" + url))){
                Clip clip = AudioSystem.getClip();

                clip.open(inputStream);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f);
                clip.start();
            } catch (Exception e) {
                System.err.println("Audio no existe: " + e.getMessage());
               // System.out.println(url);
            }
        }).start();*/
    }

    public static String parseToNumber(String str){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher mat = pattern.matcher(str);

        StringBuilder result = new StringBuilder();
        
        while (mat.find()) {             
            result.append(mat.group(0));
        }   
        
        return result.toString();
    }
    
    public static boolean storeSerializableObject(User obj, String path){

        try(OutputStream outputStream = new FileOutputStream(new File(path))){

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(obj);
            return true;
        }catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User readSerializableObject(String path){

        try(InputStream inputStream = new FileInputStream(new File(path))){

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            return (User) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

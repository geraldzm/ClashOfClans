package com.game.model;

import com.game.model.Characters.ContactWarrior;
import com.game.model.Characters.Wall;
import com.game.model.Handles.HandlerGameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 840, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false;
    private HandlerGameObjects handlerGameObjects;
    protected ImageIcon background = new ImageIcon(
            Tools.getIcon.apply("coc_bg.png")
                    .getScaledInstance(800,800, Image.SCALE_SMOOTH)
    );


    public Game(){// defalut game configuration, para pruebas

        ArrayList<Character> characters = new ArrayList<>();
        GameBoard gameBoard = new GameBoard(20,20);
        handlerGameObjects = new HandlerGameObjects();


        characters.add(new Wall(5,5));
        characters.add(new Wall(10,9));
        characters.add(new Wall(8,15));
        characters.add(new Wall(0,7));
        characters.add(new Wall(6,19));
        characters.add(new ContactWarrior(6,2, "Barbarian.png", Team.FRIEND, gameBoard));
        characters.add(new ContactWarrior(6,10, "Barbarian.png", Team.ENEMY, gameBoard));

        gameBoard.addCharacteres(characters);
        handlerGameObjects.addObjectsList(characters);
        handlerGameObjects.addObjectsList(characters);

    }

    /**
     * <h1>Configuración del juego</h1>
     * <p>Con este constructor configuramos el juego</p>
     * @param level dependiendo de esto se configura una cantidad de enemigos
     * @param friends los jugadores seleccionados por el usuario
     * */
    public Game(int level, HandlerGameObjects friends){// defalut game configuration
        
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0D;
        double ns = 1000000000D / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            boolean shouldRender = false;
            while(delta >=1) {
                tick();
                delta--;
                shouldRender = true;
            }

            if(shouldRender) {
                render();
                frames++;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(background.getImage(), 0, 0, null);

        handlerGameObjects.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handlerGameObjects.tick();
    }
}

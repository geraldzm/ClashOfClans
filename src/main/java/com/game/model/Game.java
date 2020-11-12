package com.game.model;

import com.game.controllers.Mouse;
import com.game.model.Characters.ContactWarrior;
import com.game.model.Characters.Distance;
import com.game.model.Characters.Wall;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.Clickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, Clickable {

    public static final int WIDTH = 840, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false, pause = false;
    private HandlerGameObjects handlerGameObjects;
    private GameBoard gameBoard;
    protected ImageIcon background = new ImageIcon(
            Tools.getIcon.apply("coc_bg.png")
                    .getScaledInstance(800,800, Image.SCALE_SMOOTH)
    );


    public Game(){// defalut game configuration, para pruebas

        ArrayList<Character> characters = new ArrayList<>();
        gameBoard = new GameBoard(20,20);
        handlerGameObjects = new HandlerGameObjects();

        Mouse mouse = new Mouse(this);
        this.addMouseListener(mouse);


      /*  for (int y = 0; y < 2; y++)
            for (int x = 0; x < 9; x++)
                characters.add(new ContactWarrior(x,y, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));



        for (int y = 0; y < 2; y++)
            for (int x = 10; x < 19; x++)
                characters.add(new ContactWarrior(x,y, "Barbarian.png", Team.ENEMY, gameBoard, handlerGameObjects));

*/
        characters.add(new Distance(0,0, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(1,1, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));

        characters.add(new ContactWarrior(19,19, "Barbarian.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new Distance(19,18, "Archer.png", Team.ENEMY, gameBoard, handlerGameObjects));


        gameBoard.addCharacteres(characters);
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
        if(!pause) handlerGameObjects.tick();
    }

    public void pause(){
        pause = !pause;
    }

    @Override
    public void clicked(MouseEvent e) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < gameBoard.getObjectsInGame().length; i++) {
            for (int j = 0; j < gameBoard.getObjectsInGame()[i].length; j++) {

                if(gameBoard.getObjectsInGame()[i][j] != null){
                    if(gameBoard.getObjectsInGame()[i][j] instanceof Warrior){
                        if(((Warrior)gameBoard.getObjectsInGame()[i][j]).getTeam()== Team.FRIEND)
                            stringBuilder.append(" F ");
                        else stringBuilder.append(" E ");
                    }else stringBuilder.append(" * ");
                }else
                    stringBuilder.append(" _ ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    @Override
    public void clickReleased(MouseEvent e) {
        System.out.println("Pausa");
        this.pause();
    }
}

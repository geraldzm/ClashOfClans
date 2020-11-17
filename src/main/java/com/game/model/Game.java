package com.game.model;

import com.game.controllers.Mouse;
import com.game.model.Characters.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.Clickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.Timer;

public class Game extends Canvas implements Runnable, Clickable {

    public static final int WIDTH = 840, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false, pause = false;
    private HandlerGameObjects handlerGameObjects;
    private GameBoard gameBoard;
    
    private String bgSound = "combat_music.wav";
    private Timer timerBackground; // reproduce el audio

    protected ImageIcon background = new ImageIcon(
            Tools.getIcon.apply("coc_bg.png")
                    .getScaledInstance(800,800, Image.SCALE_SMOOTH)
    );

    /**
     * <h1>Configuración de la partida</h1>
     * @param level nivel que se va a jugar
     * @param characters los personajes seleccionados por el usuario
     * @param allCharacters Todos los personajes que el usuario ha creado
     * */
    public Game(int level, ArrayList<Warrior> characters, ArrayList<Warrior> allCharacters){

        gameBoard = new GameBoard(20,20);
        handlerGameObjects = new HandlerGameObjects();

        Mouse mouse = new Mouse(this);
        this.addMouseListener(mouse);

        addUserPlayers(gameBoard, handlerGameObjects, characters); // les agrega una posicion random
        generateEnemies(gameBoard, handlerGameObjects, level, allCharacters); // generamos los enemigos segun el nivel

        timerBackground = new Timer();
        timerBackground.schedule(new TimerTask() { // el backgroun comienza en 3 segundos
            @Override
            public void run() {
                Tools.playSound(bgSound);
            }
        }, 3000, 175000);
    }

    private void addUserPlayers(GameBoard gameBoard, HandlerGameObjects handlerGameObjects, ArrayList<Warrior> characters) {
        characters.forEach(c -> {
            if(c.getId() != ID.AIR){
                c.setLocation(gameBoard.getEmptyRandomPosition());
                gameBoard.addCharacter(c);
            }
            handlerGameObjects.addObject(c);
            c.setHandlerGameObjects(handlerGameObjects);
            c.setGameBoard(gameBoard);
        });
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
    public void clickReleased(MouseEvent e) {
        System.out.println("Pausa");
        this.pause();
    }

    private void generateEnemies(GameBoard gameBoard, HandlerGameObjects handlerGameObjects, int level, ArrayList<Warrior> allCharacters) {
        Point p;
        // defensas
        for (int i = 0; i < 5; i++) { // 5 muros
            p = gameBoard.getEmptyRandomPosition();
            Wall wall = new Wall(p.x, p.y);
            gameBoard.addCharacter(wall);
            handlerGameObjects.addObject(wall);
        }

        //  2 de cada uno
        for (int j = 0; j < 2; j++) {  // AirDefense
            p = gameBoard.getEmptyRandomPosition();
            AirDefense airDefense = new AirDefense(p.x, p.y, level, gameBoard, handlerGameObjects);
            gameBoard.addCharacter(airDefense);
            handlerGameObjects.addObject(airDefense);
        }

        //  2 de cada uno
        for (int j = 0; j < 2; j++) {  // cannon
            p = gameBoard.getEmptyRandomPosition();
            Cannon cannon = new Cannon(p.x, p.y, level, gameBoard, handlerGameObjects);
            gameBoard.addCharacter(cannon);
            handlerGameObjects.addObject(cannon);
        }

        //  2 de cada uno
        for (int j = 0; j < 2; j++) {  // tower
            p = gameBoard.getEmptyRandomPosition();
            Tower tower = new Tower(p.x, p.y, level, gameBoard, handlerGameObjects);
            gameBoard.addCharacter(tower);
            handlerGameObjects.addObject(tower);
        }

        //  2 de cada uno
        for (int j = 0; j < 2; j++) {  // Mortar
            p = gameBoard.getEmptyRandomPosition();
            Mortar mortar = new Mortar(p.x, p.y, level, gameBoard, handlerGameObjects);
            gameBoard.addCharacter(mortar);
            handlerGameObjects.addObject(mortar);
        }

        //  2 de cada uno
        for (int j = 0; j < 5; j++) {  // Bomb
            p = gameBoard.getEmptyRandomPosition();
            Bomb bomb = new Bomb(p.x, p.y, level, gameBoard, handlerGameObjects);
            gameBoard.addCharacter(bomb);
            handlerGameObjects.addObject(bomb);
        }

        for (int i = 0; i < allCharacters.size(); i++) {
            if(allCharacters.get(i).appearanceLevel > level)continue;
            for (int j = 0; j < level+1; j++) {
                Warrior w = allCharacters.get(i).clone(allCharacters.get(i));

                w.setTeam(Team.ENEMY);
                w.setGameBoard(gameBoard);
                w.setHandlerGameObjects(handlerGameObjects);
                handlerGameObjects.addObject(w);

                if(w.getId() != ID.AIR) {
                    p = gameBoard.getEmptyRandomPosition();
                    w.setLocation(p);
                    gameBoard.addCharacter(w);
                }
            }
        }

        // end defensas
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
}

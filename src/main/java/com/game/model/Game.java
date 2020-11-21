package com.game.model;

import com.game.controllers.Mouse;
import com.game.model.Characters.*;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.Clickable;
import com.game.views.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

public class Game extends Canvas implements Runnable, Clickable {

    public static final int WIDTH = 840, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false, pause = false;
    private HandlerGameObjects handlerGameObjects;
    private GameBoard gameBoard;
    private GameWindow gameWindow;

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
    public Game(GameWindow gameWindow, int level, ArrayList<Warrior> characters, ArrayList<Warrior> allCharacters){
        this.gameWindow = gameWindow;
        gameBoard = new GameBoard(this,20,20);
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

    public synchronized void stopFromOutSide(){
        running = false;
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
        if(!pause){
            handlerGameObjects.tick();
            gameBoard.checkWinner();
        }
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

        int enemiesTroops = 5+3*(level-1);

        // ordenamos los personajes de de mayor campos de tropas a menor
        List<Warrior> warriorList = allCharacters.stream().sorted(Comparator.comparingInt(Warrior::getTroops).reversed()).collect(Collectors.toList());

        for (Warrior w : warriorList) {
            if (w.appearanceLevel > level) continue;
            int max = 0; // agregamos un maximo de 2 enemigos de se tipo
            while(enemiesTroops - w.troops >= 0){ // metemos todas las que se puedan de esa tropa
                if(max++ == 2) break;
                addEnemy(w);
                enemiesTroops -= w.troops;
            }
        }

        if(enemiesTroops > 0) // si nos quedan campos rellenamos
            
            for (Warrior w : warriorList) {
                if (w.appearanceLevel > level) continue;
                while(enemiesTroops - w.troops >= 0){ // metemos todas las que se puedan de esa tropa
                    addEnemy(w);
                    enemiesTroops -= w.troops;
                }
            }
    }

    private void addEnemy(Warrior w){
        Warrior wclone = w.clone(w);

        wclone.setTeam(Team.ENEMY);
        wclone.setGameBoard(gameBoard);
        wclone.setHandlerGameObjects(handlerGameObjects);
        handlerGameObjects.addObject(wclone);

        if (wclone.getId() != ID.AIR) {
            wclone.setLocation(gameBoard.getEmptyRandomPosition());
            gameBoard.addCharacter(wclone);
        }
    }


    /**
     * Llamamos este metodo si hay un winner
     * */
    public void winner(Team winner){
        System.out.println("gana equipo: " + winner);

        /*if(winner == Team.FRIEND){ // si el usuario ganó
            // mostrar animacion de que gano
        }else{
            // mostrar animacion de que perdio
        }*/

        gameWindow.weHaveAWinner(winner);
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

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
    private boolean running = false, pause = false, winner = false;
    private HandlerGameObjects handlerGameObjects;
    private GameBoard gameBoard;
    private GameWindow gameWindow;
    private String bgSound = "combat_music.wav";
    private Team winnerTeam;
    private int level; // nivel que estamos jugando

    private ImageIcon winnerImg = new ImageIcon(
            Tools.getIcon.apply("winner.png")
                    .getScaledInstance(400,100, Image.SCALE_SMOOTH)
    );
    private ImageIcon loserImg = new ImageIcon(
            Tools.getIcon.apply("loser.png")
                    .getScaledInstance(400,100, Image.SCALE_SMOOTH)
    );
    private ImageIcon backButton = new ImageIcon(
            Tools.getIcon.apply("back_btn.png")
                    .getScaledInstance(100,50, Image.SCALE_SMOOTH)
    );

    protected ImageIcon background = new ImageIcon(
            Tools.getIcon.apply("coc_bg.png")
                    .getScaledInstance(800,800, Image.SCALE_SMOOTH)
    );

    protected ImageIcon backgroundEnd = new ImageIcon(
            Tools.getIcon.apply("coc_bg_ended.png")
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
        this.level = level;

        Mouse mouse = new Mouse(this);
        this.addMouseListener(mouse);

        addUserPlayers(gameBoard, handlerGameObjects, characters); // les agrega una posicion random
        generateEnemies(gameBoard, handlerGameObjects, level, allCharacters); // generamos los enemigos segun el nivel

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

        if(winner) renderWinner(g);
        else handlerGameObjects.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        if(winner)tickWinner();
        else if(!pause){
            handlerGameObjects.tick();
            gameBoard.checkWinner();
        }
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
        wclone.upgrade(level);

        if (wclone.getId() != ID.AIR) {
            wclone.setLocation(gameBoard.getEmptyRandomPosition());
            gameBoard.addCharacter(wclone);
        }
    }


    /**
     * Llamamos este metodo si hay un winner
     * */
    public void winner(Team winner){
        this.winner = true;
        System.out.println("gana equipo: " + winner);

        winnerTeam = winner;

        //gameWindow.weHaveAWinner(winner); // llamamos esto para devolvernos a la pantalla principal
    }

    public void renderWinner(Graphics g){ // se usa para renderizar la animacion del winner
        ImageIcon img = (winnerTeam == Team.FRIEND) ? winnerImg : loserImg;
        g.drawImage(backgroundEnd.getImage(), 0, 0, null);
        g.drawImage(img.getImage(), 200, 400, null);

        // Renderizado del boton
        g.drawImage(backButton.getImage(), 350, 550, null);
    }

    public void tickWinner(){ // se usa para el winner
    }

    public void pause(){
        pause = !pause;
    }

    @Override
    public void clickReleased(MouseEvent e) {
        if (winner){
            if (350 <= e.getX() && e.getX() <= 450)
                if (550 <= e.getY() && e.getY() <= 600)
                    gameWindow.weHaveAWinner(winnerTeam, this.level);
        } else {
            this.pause();
        }
    }

    @Override
    public void clicked(MouseEvent e) {
    }
}

package com.game.model;

import com.game.controllers.Mouse;
import com.game.model.Characters.AirDefense;
import com.game.model.Characters.AirWarrior;
import com.game.model.Characters.Beast;
import com.game.model.Characters.Bomb;
import com.game.model.Characters.ContactWarrior;
import com.game.model.Characters.Distance;
import com.game.model.Characters.Heroe;
import com.game.model.Characters.LandDefense;
import com.game.model.Characters.Wall;
import com.game.model.Handles.HandlerGameObjects;
import com.game.model.Interfaces.Clickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Date;

public class Game extends Canvas implements Runnable, Clickable {

    public static final int WIDTH = 840, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false, pause = false;
    private HandlerGameObjects handlerGameObjects;
    private GameBoard gameBoard;
    
    private String bgSound = "combat_music.wav";
    private Date sinceBGSPlayed;
    
    protected ImageIcon background = new ImageIcon(
            Tools.getIcon.apply("coc_bg.png")
                    .getScaledInstance(800,800, Image.SCALE_SMOOTH)
    );


    public Game(){// defalut game configuration, para pruebas
        sinceBGSPlayed = new Date();
        Tools.playSound(bgSound);
        
        ArrayList<Character> characters = new ArrayList<>();
        gameBoard = new GameBoard(20,20);
        handlerGameObjects = new HandlerGameObjects();

        Mouse mouse = new Mouse(this);
        this.addMouseListener(mouse);
        
        // XD Me encanta
        
        characters.add(new Wall(6, 0));
        characters.add(new Wall(6, 1));
        characters.add(new Wall(6, 2));
        characters.add(new Wall(6, 3));
        characters.add(new Wall(6, 4));
        characters.add(new Wall(6, 5));
        characters.add(new Wall(7, 5));
        characters.add(new Wall(7, 6));
        characters.add(new Wall(8, 6));
        characters.add(new Wall(8, 7));
        characters.add(new Wall(8, 8));
        characters.add(new Wall(8, 9));
        characters.add(new Wall(8, 10));
        
        // Defensas
        characters.add(new Defense(10, 13, "Archer_Tower.png", gameBoard, handlerGameObjects));
        characters.add(new Defense(12, 12, "Archer_Tower.png", gameBoard, handlerGameObjects));
        characters.add(new Defense(14, 11, "Archer_Tower.png", gameBoard, handlerGameObjects));
        characters.add(new Defense(16, 10, "Archer_Tower.png", gameBoard, handlerGameObjects));
        
        characters.add(new LandDefense(10, 15, "Cannon.png", gameBoard, handlerGameObjects));
        characters.add(new LandDefense(18, 10, "Cannon.png", gameBoard, handlerGameObjects));
        
        characters.add(new LandDefense(13, 14, "Mortar.png", gameBoard, handlerGameObjects));
        characters.add(new LandDefense(16, 14, "Mortar.png", gameBoard, handlerGameObjects));
        characters.add(new LandDefense(16, 12, "Mortar.png", gameBoard, handlerGameObjects));
        
        characters.add(new AirDefense(5, 14, "Air_Defense.png", gameBoard, handlerGameObjects));
        characters.add(new AirDefense(18, 8, "Air_Defense.png", gameBoard, handlerGameObjects));
        
        characters.add(new Bomb(4, 1, "Bomb.png", gameBoard, handlerGameObjects));
        characters.add(new Bomb(9, 5, "Bomb.png", gameBoard, handlerGameObjects));
        
        // Enemigos
        characters.add(new ContactWarrior(9, 3, "Barbarian.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(11, 5, "Barbarian.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(13, 7, "Barbarian.png", Team.ENEMY, gameBoard, handlerGameObjects));
        
        characters.add(new Distance(12, 0, "Archer.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new Distance(12, 2, "Archer.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new Distance(13, 4, "Archer.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new Distance(15, 6, "Archer.png", Team.ENEMY, gameBoard, handlerGameObjects));
        
        characters.add(new AirWarrior("Dragon.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new AirWarrior("Dragon.png", Team.ENEMY, gameBoard, handlerGameObjects));
        
        characters.add(new Beast(18, 3, "PEKKA.png", Team.ENEMY, gameBoard, handlerGameObjects));
        characters.add(new Beast(17, 1, "PEKKA.png", Team.ENEMY, gameBoard, handlerGameObjects));
        
        // Equipo del jugador
        characters.add(new ContactWarrior(3, 5, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(4, 6, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(6, 8, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new ContactWarrior(7, 10, "Barbarian.png", Team.FRIEND, gameBoard, handlerGameObjects));
        
        characters.add(new Distance(0, 4, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Distance(2, 6, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Distance(4, 8, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Distance(6, 10, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Distance(6, 12, "Archer.png", Team.FRIEND, gameBoard, handlerGameObjects));
        
        characters.add(new AirWarrior("Dragon.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new AirWarrior("Dragon.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new AirWarrior("Dragon.png", Team.FRIEND, gameBoard, handlerGameObjects));
        
        characters.add(new Beast(1, 8, "PEKKA.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Beast(2, 10, "PEKKA.png", Team.FRIEND, gameBoard, handlerGameObjects));
        characters.add(new Beast(1, 12, "PEKKA.png", Team.FRIEND, gameBoard, handlerGameObjects));
        
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
            playBGSound();
        }
        stop();
    }
    
    private void playBGSound(){
        if (sinceBGSPlayed.getTime() - new Date().getTime() >= 175000){
            Tools.playSound(bgSound);
            sinceBGSPlayed = new Date();
        }
            
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

package com.game.views;

import com.game.model.Characters.AirWarrior;
import com.game.model.Characters.ContactWarrior;
import com.game.model.Characters.Distance;
import com.game.model.Game;
import com.game.model.Tools;
import com.game.model.User;
import com.game.model.Warrior;

import java.awt.BorderLayout;
import java.util.ArrayList;

public class GameWindow extends javax.swing.JFrame {

    public GameWindow() {

        User user = Tools.readSerializableObject("/home/gerald/develop/poo/ClashOfClans/src/main/java/com/game/Games/user1.game");
        System.out.println(user);

        ArrayList<Warrior> characters = new ArrayList<>();

        for (int j = 0; j < 7; j++){
            for (int i = 0; i < user.getAllCharacters().size(); i++){

                characters.add(user.getAllCharacters().get(i).clone(user.getAllCharacters().get(i)));

            }
        }


        Game game = new Game(1, characters, user.getAllCharacters());


        add(game, BorderLayout.CENTER); // de momento, luego se le pasan las configs al game por el constructor
        initComponents();
        game.start();
        setSize(800, 855);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        menuDebug = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        menuBar.setBackground(new java.awt.Color(0, 0, 0));

        menuDebug.setText("Debug");
        menuBar.add(menuDebug);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDebug;
    // End of variables declaration//GEN-END:variables
}

package com.game.views;

import com.game.model.Game;
import java.awt.BorderLayout;

public class GameWindow extends javax.swing.JFrame {

    public GameWindow() {
        Game game = new Game();
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

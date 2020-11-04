package com.game.views;

public class GameWindow extends javax.swing.JFrame {

    public GameWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cvGame = new java.awt.Canvas();
        menuBar = new javax.swing.JMenuBar();
        menuDebug = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        cvGame.setBackground(new java.awt.Color(0, 0, 0));
        cvGame.setMinimumSize(new java.awt.Dimension(800, 600));
        cvGame.setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().add(cvGame, java.awt.BorderLayout.CENTER);

        menuBar.setBackground(new java.awt.Color(0, 0, 0));

        menuDebug.setText("Debug");
        menuBar.add(menuDebug);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas cvGame;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDebug;
    // End of variables declaration//GEN-END:variables
}

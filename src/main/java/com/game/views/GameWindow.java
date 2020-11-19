package com.game.views;


import com.game.model.*;

import java.awt.*;

public class GameWindow extends javax.swing.JFrame {

    private Game game;
    private User user;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDebug;

    public GameWindow(User user) {
        this.user = user;
        System.out.println(user.getToPlay());
        System.out.println(user.getAllCharacters());

        this.game = new Game(this, user.getLevel(), user.getToPlay(), user.getAllCharacters());

        add(this.game, BorderLayout.CENTER); // de momento, luego se le pasan las configs al game por el constructor
        initComponents();
        this.game.start();

        setSize(800, 860);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        menuDebug = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(1080, 720));
        setResizable(false);

        menuBar.setBackground(new java.awt.Color(0, 0, 0));

        menuDebug.setText("Debug");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
        jMenuItem1.setText("Volver");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuDebug.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 0));
        jMenuItem2.setText("Pausar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuDebug.add(jMenuItem2);

        jMenu1.setText("Ganar");
        menuDebug.add(jMenu1);

        menuBar.add(menuDebug);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        backToMenu();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        game.pause();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * <h1>Si alguien gana</h1>
     * <p>Game llama a este metodo cuando alguien gana</p>
     * */
    public void weHaveAWinner(Team team){
        if(team == Team.FRIEND){// el usuario gano, incrementamos los niveles de sus najes y su nivel
            user.getCharacters().forEach(Fighter::levelUp);
            user.levelUp();
        }
        backToMenu();
    }

    private void backToMenu(){
        game.stopFromOutSide();
        new MainWindow(user).setVisible(true);
        this.dispose();
    }

    // End of variables declaration//GEN-END:variables
}

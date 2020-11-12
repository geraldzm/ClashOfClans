package com.game.views;

import com.game.model.Tools;
import java.io.IOException;

public class TroopsWindow extends javax.swing.JFrame {

    public TroopsWindow() throws IOException {
        initComponents();
        background.setIcon(new Tools().getComponentIcon("res/bg_troop.png", background.getWidth(), background.getHeight()));
        btnPlay.setIcon(new Tools().getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        troopsContainer = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnPlay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(247, 247, 247));
        setMinimumSize(new java.awt.Dimension(800, 400));
        setPreferredSize(new java.awt.Dimension(800, 400));
        setSize(new java.awt.Dimension(800, 400));
        getContentPane().setLayout(null);
        getContentPane().add(troopsContainer);
        troopsContainer.setBounds(280, 30, 260, 300);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Jugador Nivel: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 190, 180, 25);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(30, 140, 180, 30);

        btnPlay.setPreferredSize(new java.awt.Dimension(200, 100));
        getContentPane().add(btnPlay);
        btnPlay.setBounds(640, 270, 120, 60);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Seleccionar tropa");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 100, 180, 25);
        getContentPane().add(background);
        background.setBounds(0, 0, 800, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnPlay;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane troopsContainer;
    // End of variables declaration//GEN-END:variables
}

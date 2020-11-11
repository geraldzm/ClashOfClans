package com.game.views;

import com.game.model.Tools;
import java.io.IOException;

public class TroopsWindow extends javax.swing.JFrame {

    public TroopsWindow() throws IOException {
        initComponents();
        btnPlay.setIcon(new Tools().getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        btnRemove.setIcon(new Tools().getComponentIcon("res/remove_button.png", btnRemove.getWidth(), btnRemove.getHeight()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ContainerSelect = new javax.swing.JScrollPane();
        ContainerTroops = new javax.swing.JScrollPane();
        btnRemove = new javax.swing.JLabel();
        btnPlay = new javax.swing.JLabel();
        lbTroop = new javax.swing.JLabel();
        lbSelected = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(247, 247, 247));
        setPreferredSize(new java.awt.Dimension(800, 550));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Tropas:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 6, 76, 32);

        ContainerSelect.setBackground(new java.awt.Color(229, 229, 229));
        getContentPane().add(ContainerSelect);
        ContainerSelect.setBounds(427, 56, 343, 334);

        ContainerTroops.setBackground(new java.awt.Color(229, 229, 229));
        getContentPane().add(ContainerTroops);
        ContainerTroops.setBounds(30, 56, 333, 336);
        getContentPane().add(btnRemove);
        btnRemove.setBounds(233, 410, 130, 60);

        btnPlay.setPreferredSize(new java.awt.Dimension(200, 100));
        getContentPane().add(btnPlay);
        btnPlay.setBounds(650, 410, 120, 60);

        lbTroop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(lbTroop);
        lbTroop.setBounds(238, 6, 125, 32);

        lbSelected.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(lbSelected);
        lbSelected.setBounds(645, 6, 125, 32);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ContainerSelect;
    private javax.swing.JScrollPane ContainerTroops;
    private javax.swing.JLabel btnPlay;
    private javax.swing.JLabel btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbSelected;
    private javax.swing.JLabel lbTroop;
    // End of variables declaration//GEN-END:variables
}

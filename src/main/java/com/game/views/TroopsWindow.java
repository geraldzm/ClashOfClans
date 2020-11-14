package com.game.views;

import com.game.model.Tools;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TroopsWindow extends javax.swing.JFrame {

    public TroopsWindow() throws IOException {
        initComponents();
        background.setIcon(new Tools().getComponentIcon("res/bg_troop.png", background.getWidth(), background.getHeight()));
        btnPlay.setIcon(new Tools().getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        btnBack.setIcon(new Tools().getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        troopsContainer = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnPlay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(247, 247, 247));
        setMinimumSize(new java.awt.Dimension(800, 400));
        setSize(new java.awt.Dimension(800, 400));
        getContentPane().setLayout(null);

        btnBack.setPreferredSize(new java.awt.Dimension(200, 100));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(640, 190, 120, 60);
        getContentPane().add(troopsContainer);
        troopsContainer.setBounds(280, 30, 260, 300);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tropas (current/max)");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 230, 180, 25);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(30, 140, 180, 30);

        btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay.setPreferredSize(new java.awt.Dimension(200, 100));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPlayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlayMouseExited(evt);
            }
        });
        getContentPane().add(btnPlay);
        btnPlay.setBounds(640, 270, 120, 60);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Seleccionar tropa");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 100, 180, 25);
        getContentPane().add(background);
        background.setBounds(0, 0, 800, 400);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Jugador Nivel: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 190, 180, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        new MainWindow().setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        new GameWindow().setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btnPlayMouseClicked
    
    // Animaciones
    private void btnPlayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseEntered
        try {
            btnPlay.setIcon(new Tools().getComponentIcon("res/play_focus_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(TroopsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPlayMouseEntered

    private void btnPlayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseExited
        try {
            btnPlay.setIcon(new Tools().getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(TroopsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPlayMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        try {
            btnBack.setIcon(new Tools().getComponentIcon("res/back_focus_button.png", btnBack.getWidth(), btnBack.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(TroopsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        try {
            btnBack.setIcon(new Tools().getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(TroopsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnPlay;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane troopsContainer;
    // End of variables declaration//GEN-END:variables
}

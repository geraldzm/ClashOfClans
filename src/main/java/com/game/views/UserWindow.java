package com.game.views;

import com.game.model.Tools;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserWindow extends javax.swing.JFrame {

    public UserWindow() throws IOException {
        initComponents();
        
        header.setIcon(Tools.getComponentIcon("res/header.png", header.getWidth(), header.getHeight()));
        background.setIcon(Tools.getComponentIcon("res/bg_troop.png", background.getWidth(), background.getHeight()));
        btnBack.setIcon(Tools.getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(699, 423));
        setSize(new java.awt.Dimension(699, 423));
        getContentPane().setLayout(null);

        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(540, 310, 130, 60);

        title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Configuracion");
        getContentPane().add(title);
        title.setBounds(280, 0, 130, 25);

        header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(header);
        header.setBounds(0, 0, 700, 40);
        getContentPane().add(background);
        background.setBounds(0, 0, 700, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        new MainWindow().setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnBackMousePressed

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        try {
            btnBack.setIcon(Tools.getComponentIcon("res/back_focus_button.png", btnBack.getWidth(), btnBack.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        try {
            btnBack.setIcon(Tools.getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel header;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

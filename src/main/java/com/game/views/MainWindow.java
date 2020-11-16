package com.game.views;

import com.game.model.Tools;
import com.game.model.User;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {
    
    private User user;
    
    // Constructor del main
    public MainWindow() {
        initComponents();
        
        initImages();
        
        user = new User("Admin", "1234");
    }
    
    // Constructor una vez hay usuario cargado
    public MainWindow(User user){
        initComponents();
        
        initImages();
        
        this.user = user;
    }
    
    private void setLabelImage(JLabel label, String path){
        try {
            label.setIcon(Tools.getComponentIcon(path, label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initImages(){
        try {
            background.setIcon(Tools.getComponentIcon("res/bg.png", background.getWidth(), background.getHeight()));
            Logo.setIcon(Tools.getComponentIcon("res/logo.png", Logo.getWidth(), Logo.getHeight()));

            btnPlay.setIcon(Tools.getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
            btnLoad.setIcon(Tools.getComponentIcon("res/load_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
            btnConfig.setIcon(Tools.getComponentIcon("res/config_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Logo = new javax.swing.JLabel();
        btnLoad = new javax.swing.JLabel();
        btnConfig = new javax.swing.JLabel();
        btnPlay = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(null);
        getContentPane().add(Logo);
        Logo.setBounds(240, 40, 320, 180);

        btnLoad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoadMouseExited(evt);
            }
        });
        getContentPane().add(btnLoad);
        btnLoad.setBounds(490, 280, 150, 80);

        btnConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfigMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigMouseExited(evt);
            }
        });
        getContentPane().add(btnConfig);
        btnConfig.setBounds(110, 280, 170, 80);

        btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnPlayFocusGained(evt);
            }
        });
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
        btnPlay.setBounds(310, 280, 140, 80);
        getContentPane().add(background);
        background.setBounds(0, 0, 810, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnPlayFocusGained

    }//GEN-LAST:event_btnPlayFocusGained

    // Animaciones
    private void btnPlayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseEntered
        setLabelImage(btnPlay, "res/play_focus_button.png");
     }//GEN-LAST:event_btnPlayMouseEntered

    private void btnPlayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseExited
        setLabelImage(btnPlay, "res/play_button.png");
    }//GEN-LAST:event_btnPlayMouseExited

    private void btnConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseEntered
        setLabelImage(btnConfig, "res/config_focus_button.png");
    }//GEN-LAST:event_btnConfigMouseEntered

    private void btnConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseExited
        setLabelImage(btnConfig, "res/config_button.png");
    }//GEN-LAST:event_btnConfigMouseExited

    private void btnLoadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadMouseEntered
        setLabelImage(btnLoad, "res/load_focus_button.png");
    }//GEN-LAST:event_btnLoadMouseEntered

    private void btnLoadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadMouseExited
        setLabelImage(btnLoad, "res/load_button.png");
    }//GEN-LAST:event_btnLoadMouseExited
    
    // Eventos
    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        try {
            new TroopsWindow(user).setVisible(true);
            
            this.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPlayMouseClicked

    private void btnConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseClicked
        try {
            String pass = JOptionPane.showInputDialog("Usuario: "+user.getName()+ "\nDigite su contraseña:");
            
            if (user.login(pass)){
                new UserWindow(user).setVisible(true);
                
                this.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfigMouseClicked

    private void btnLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadMouseClicked
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        
        if(f.length > 0){
            User loading;
            String path = fd.getFiles()[0].getAbsolutePath();
            loading = Tools.readSerializableObject(path);
            
            if (loading != null){
                JOptionPane.showMessageDialog(rootPane, "Usuario cargado!");
                user = loading;
            }else{
                JOptionPane.showMessageDialog(rootPane, "El archivo esta corrupto!");
            }
        }
    }//GEN-LAST:event_btnLoadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnConfig;
    private javax.swing.JLabel btnLoad;
    private javax.swing.JLabel btnPlay;
    // End of variables declaration//GEN-END:variables
}

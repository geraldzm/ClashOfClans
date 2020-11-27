package com.game.views;

import com.game.model.Characters.*;
import com.game.model.ID;
import com.game.model.Tools;
import com.game.model.User;
import com.game.model.Warrior;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserWindow extends javax.swing.JFrame {

    private User user;
    private String[] tipos = {"Aereo", "Bestia", "Contacto", "Distancia", "Heroe"};
    private ID[] ids = {ID.AIR, ID.BEAST, ID.CONTACT, ID.DISTANCE, ID.HERO};

    private ImageIcon[] images = new ImageIcon[3];
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBackup;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnImagen;
    private javax.swing.JLabel btnImagen2;
    private javax.swing.JLabel btnImagen3;
    private javax.swing.JLabel btnSave;
    private javax.swing.JComboBox<String> cbAvailableTroops;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel header;
    private javax.swing.JLabel lbSelected;
    private javax.swing.JLabel lbSelected1;
    private javax.swing.JTextField tfAparicion;
    private javax.swing.JTextField tfAtaque;
    private javax.swing.JTextField tfCampos;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfSpeed;
    private javax.swing.JTextField tfVida;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;

    public UserWindow(User user) throws IOException {
        initComponents();

        this.user = user;

        initComboBox();

        header.setIcon(Tools.getComponentIcon("res/header.png", header.getWidth(), header.getHeight()));
        background.setIcon(Tools.getComponentIcon("res/bg_troop.png", background.getWidth(), background.getHeight()));
        btnBack.setIcon(Tools.getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
        btnSave.setIcon(Tools.getComponentIcon("res/save_button.png", btnSave.getWidth(), btnSave.getHeight()));
        btnDelete.setIcon(Tools.getComponentIcon("res/delete_button.png", btnDelete.getWidth(), btnDelete.getHeight()));
        btnBackup.setIcon(Tools.getComponentIcon("res/backup_button.png", btnBackup.getWidth(), btnBackup.getHeight()));

        new TextPrompt("Nombre de la tropa...", tfNombre);
        new TextPrompt("Nivel de aparicion...", tfAparicion);
        new TextPrompt("Poder ataque...", tfAtaque);
        new TextPrompt("Campos de la tropa...", tfCampos);
        new TextPrompt("Velocidad de la tropa...", tfSpeed);
        new TextPrompt("Vida de la tropa...", tfVida);
        setLocationRelativeTo(null);
    }

    private void initComboBox() {
        for (int i = 0; i < user.getAllCharacters().size(); i++) {
            cbAvailableTroops.addItem(user.getAllCharacters().get(i).getName());
        }

        // Se que no le gusta el foreach! pero en este caso no da problemas! no lo cambie...
        for (String tipo : tipos) {
            cbTipo.addItem(tipo);
        }
    }

    private void setLabelImage(JLabel label, String path) {
        try {
            label.setIcon(Tools.getComponentIcon(path, label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTipo = new javax.swing.JComboBox<>();
        lbSelected1 = new javax.swing.JLabel();
        lbSelected = new javax.swing.JLabel();
        btnBackup = new javax.swing.JLabel();
        btnSave = new javax.swing.JLabel();
        tfSpeed = new javax.swing.JTextField();
        tfNombre = new javax.swing.JTextField();
        tfVida = new javax.swing.JTextField();
        tfAtaque = new javax.swing.JTextField();
        tfCampos = new javax.swing.JTextField();
        tfAparicion = new javax.swing.JTextField();
        title4 = new javax.swing.JLabel();
        title3 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        btnImagen3 = new javax.swing.JLabel();
        btnImagen2 = new javax.swing.JLabel();
        btnImagen = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        cbAvailableTroops = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(837, 530));
        setResizable(false);
        setSize(new java.awt.Dimension(837, 530));
        getContentPane().setLayout(null);

        getContentPane().add(cbTipo);
        cbTipo.setBounds(290, 250, 330, 22);

        lbSelected1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSelected1.setForeground(new java.awt.Color(255, 255, 255));
        lbSelected1.setText("Tropa seleccionada: ");
        getContentPane().add(lbSelected1);
        lbSelected1.setBounds(30, 150, 180, 25);

        lbSelected.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSelected.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbSelected);
        lbSelected.setBounds(30, 180, 180, 40);

        btnBackup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackupMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackupMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackupMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackupMousePressed(evt);
            }
        });
        getContentPane().add(btnBackup);
        btnBackup.setBounds(30, 250, 160, 70);

        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveMousePressed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(670, 250, 130, 60);

        tfSpeed.setBackground(new java.awt.Color(255, 255, 255));
        tfSpeed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSpeedKeyReleased(evt);
            }
        });
        getContentPane().add(tfSpeed);
        tfSpeed.setBounds(290, 410, 330, 22);

        tfNombre.setBackground(new java.awt.Color(255, 255, 255));
        tfNombre.setToolTipText("Nombre");
        getContentPane().add(tfNombre);
        tfNombre.setBounds(290, 290, 330, 22);

        tfVida.setBackground(new java.awt.Color(255, 255, 255));
        tfVida.setToolTipText("");
        tfVida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfVidaKeyReleased(evt);
            }
        });
        getContentPane().add(tfVida);
        tfVida.setBounds(290, 320, 330, 22);

        tfAtaque.setBackground(new java.awt.Color(255, 255, 255));
        tfAtaque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAtaqueKeyReleased(evt);
            }
        });
        getContentPane().add(tfAtaque);
        tfAtaque.setBounds(290, 350, 330, 22);

        tfCampos.setBackground(new java.awt.Color(255, 255, 255));
        tfCampos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCamposKeyReleased(evt);
            }
        });
        getContentPane().add(tfCampos);
        tfCampos.setBounds(290, 380, 330, 22);

        tfAparicion.setBackground(new java.awt.Color(255, 255, 255));
        tfAparicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAparicionKeyReleased(evt);
            }
        });
        getContentPane().add(tfAparicion);
        tfAparicion.setBounds(290, 440, 330, 22);

        title4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title4.setForeground(new java.awt.Color(255, 255, 255));
        title4.setText("Img muerte");
        getContentPane().add(title4);
        title4.setBounds(650, 50, 150, 25);

        title3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title3.setForeground(new java.awt.Color(255, 255, 255));
        title3.setText("Img ataque");
        getContentPane().add(title3);
        title3.setBounds(470, 50, 150, 25);

        title2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title2.setForeground(new java.awt.Color(255, 255, 255));
        title2.setText("Img normal");
        getContentPane().add(title2);
        title2.setBounds(290, 50, 150, 25);

        btnImagen3.setBackground(new java.awt.Color(255, 255, 255));
        btnImagen3.setOpaque(true);
        btnImagen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImagen3MouseClicked(evt);
            }
        });
        getContentPane().add(btnImagen3);
        btnImagen3.setBounds(650, 80, 150, 150);

        btnImagen2.setBackground(new java.awt.Color(255, 255, 255));
        btnImagen2.setOpaque(true);
        btnImagen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImagen2MouseClicked(evt);
            }
        });
        getContentPane().add(btnImagen2);
        btnImagen2.setBounds(470, 80, 150, 150);

        btnImagen.setBackground(new java.awt.Color(255, 255, 255));
        btnImagen.setOpaque(true);
        btnImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImagenMouseClicked(evt);
            }
        });
        getContentPane().add(btnImagen);
        btnImagen.setBounds(290, 80, 150, 150);

        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(30, 350, 160, 70);

        title1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setText("Tropas disponibles");
        getContentPane().add(title1);
        title1.setBounds(30, 60, 170, 25);

        cbAvailableTroops.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAvailableTroopsItemStateChanged(evt);
            }
        });
        getContentPane().add(cbAvailableTroops);
        cbAvailableTroops.setBounds(30, 100, 160, 22);

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
        btnBack.setBounds(670, 330, 130, 60);

        title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Configuracion");
        getContentPane().add(title);
        title.setBounds(350, 0, 170, 25);

        header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(header);
        header.setBounds(0, 0, 840, 40);
        getContentPane().add(background);
        background.setBounds(0, 0, 840, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        new MainWindow(user).setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnBackMousePressed

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        setLabelImage(btnBack, "res/back_focus_button.png");
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        setLabelImage(btnBack, "res/back_button.png");
    }//GEN-LAST:event_btnBackMouseExited

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        setLabelImage(btnSave, "res/save_focus_button.png");
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        setLabelImage(btnSave, "res/save_button.png");
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnBackupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackupMouseEntered
        setLabelImage(btnBackup, "res/backup_focus_button.png");
    }//GEN-LAST:event_btnBackupMouseEntered

    private void btnBackupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackupMouseExited
        setLabelImage(btnBackup, "res/backup_button.png");
    }//GEN-LAST:event_btnBackupMouseExited

    private void btnBackupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackupMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackupMousePressed

    private void tfVidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVidaKeyReleased
        tfVida.setText(Tools.parseToNumber(tfVida.getText()));
    }//GEN-LAST:event_tfVidaKeyReleased

    private void tfAtaqueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAtaqueKeyReleased
        tfAtaque.setText(Tools.parseToNumber(tfAtaque.getText()));
    }//GEN-LAST:event_tfAtaqueKeyReleased

    private void tfCamposKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCamposKeyReleased
        tfCampos.setText(Tools.parseToNumber(tfCampos.getText()));
    }//GEN-LAST:event_tfCamposKeyReleased

    private void tfAparicionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAparicionKeyReleased
        tfAparicion.setText(Tools.parseToNumber(tfAparicion.getText()));
    }//GEN-LAST:event_tfAparicionKeyReleased

    private void btnBackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackupMouseClicked

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardando el backup");

        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tools.storeSerializableObject(user, fc.getSelectedFile().getPath());
            JOptionPane.showMessageDialog(rootPane, "Backup guardado!");
        }
    }//GEN-LAST:event_btnBackupMouseClicked

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        setLabelImage(btnDelete, "res/delete_focus_button.png");
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        setLabelImage(btnDelete, "res/delete_button.png");
    }//GEN-LAST:event_btnDeleteMouseExited

    private Warrior getWarrior(ID id) {
        int level = Integer.parseInt(tfAparicion.getText());
        int attack = Integer.parseInt(tfAtaque.getText());
        int troops = Integer.parseInt(tfCampos.getText());
        int health = Integer.parseInt(tfVida.getText());
        int speed = Integer.parseInt(tfSpeed.getText());

        String name = tfNombre.getText();

        Warrior warrior = null;

        switch (id) {
            case AIR:
                warrior = new AirWarrior(health, name, troops, level, 4, attack, speed, images);
                break;
            case BEAST:
                warrior = new Beast(health, name, troops, level, 3, attack, speed, images);
                break;
            case CONTACT:
                warrior = new ContactWarrior(health, name, troops, level, 1, attack, speed, images);
                break;
            case DISTANCE:
                warrior = new Distance(health, name, troops, level, 3, attack, speed, images);
                break;
            case HERO:
                warrior = new Heroe(health, name, troops, level, 1, attack, speed, images);
                break;
            default:
                System.out.println("wtf no deberia llegar aqui...");
                JOptionPane.showConfirmDialog(rootPane, "Seleccione un tripo de tropa valido");
                break;
        }

        return warrior;
    }

    private boolean areImagesEmpty() {
        for (int i = 0; i < images.length; i++) {
            if (images[i] == null) {
                JOptionPane.showMessageDialog(rootPane, "La imagen #" + (i + 1) + " no ha sido seleccionada");
                return true;
            }
        }

        return false;
    }

    private boolean areTextFieldsEmpty() {
        JTextField[] textFields = {tfAparicion, tfAtaque, tfCampos, tfNombre, tfSpeed, tfVida};

        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Rellene todos los campos...");
                return true;
            }
        }

        return false;
    }

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        int index = cbTipo.getSelectedIndex();

        if (index == -1 || areImagesEmpty() || user == null || areTextFieldsEmpty()) return;

        Warrior toAdd = getWarrior(ids[index]);

        if(user.getWarriorByName(toAdd.getName()) == null){
            user.addCreatedCharacter(toAdd);
            emptyFields();
            JOptionPane.showMessageDialog(rootPane, "Agregado!");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Ya existe un personaje con ese nombre!");
        }


    }//GEN-LAST:event_btnSaveMouseClicked

    private void emptyFields() {
        tfAparicion.setText("");
        tfAtaque.setText("");
        tfCampos.setText("");
        tfVida.setText("");
        tfSpeed.setText("");
        tfNombre.setText("");

        images = new ImageIcon[3];

        btnImagen.setIcon(null);
        btnImagen2.setIcon(null);
        btnImagen3.setIcon(null);

        cbAvailableTroops.removeAllItems();

        for (int i = 0; i < user.getAllCharacters().size(); i++) {
            cbAvailableTroops.addItem(user.getAllCharacters().get(i).getName());
        }
    }

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        int selected = cbAvailableTroops.getSelectedIndex();

        if (selected != -1) {
            //user.getAllCharacters().remove(selected);
            user.deleteCharacter(cbAvailableTroops.getSelectedItem().toString());

            cbAvailableTroops.removeAllItems();

            for (int i = 0; i < user.getAllCharacters().size(); i++) {
                cbAvailableTroops.addItem(user.getAllCharacters().get(i).getName());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay ningun guerrero seleccionado...");
        }

    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImagenMouseClicked
        setImage(btnImagen, 0);
    }//GEN-LAST:event_btnImagenMouseClicked

    private void btnImagen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImagen2MouseClicked
        setImage(btnImagen2, 1);
    }//GEN-LAST:event_btnImagen2MouseClicked

    private void btnImagen3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImagen3MouseClicked
        setImage(btnImagen3, 2);
    }//GEN-LAST:event_btnImagen3MouseClicked

    private void tfSpeedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSpeedKeyReleased
        tfSpeed.setText(Tools.parseToNumber(tfSpeed.getText()));
    }//GEN-LAST:event_tfSpeedKeyReleased

    private void cbAvailableTroopsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAvailableTroopsItemStateChanged
        int selected = cbAvailableTroops.getSelectedIndex();
        String troop = "";

        if (selected != -1)
            troop = user.getAllCharacters().get(selected).getName();

        lbSelected.setText(troop);
    }//GEN-LAST:event_cbAvailableTroopsItemStateChanged

    private void setImage(javax.swing.JLabel label, int index) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Seleccione la imagen");

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            try {
                images[index] = Tools.getComponentIcon(path, 40, 40); // cuando escala mata las animaciones de los gifs
                label.setIcon(Tools.getComponentIcon(path, label.getWidth(), label.getHeight()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "El archivo no es una imagen...");
            }
        }
    }
    // End of variables declaration//GEN-END:variables
}

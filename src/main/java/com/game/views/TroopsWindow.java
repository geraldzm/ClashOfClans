package com.game.views;

import com.game.model.Tools;
import com.game.model.User;
import com.game.model.Warrior;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class TroopsWindow extends javax.swing.JFrame {
    
    private final User user;
    private final ArrayList<Warrior> warriors;
    private final DefaultTableModel tbModel;
    private final int totalTroops;
    private int currentTroops;
    private int posInTable;
    
    public TroopsWindow(User user) throws IOException {
        initComponents();
        
        warriors = new ArrayList<>();
        
        header.setIcon(new Tools().getComponentIcon("res/header.png", header.getWidth(), header.getHeight()));
        background.setIcon(new Tools().getComponentIcon("res/bg_troop.png", background.getWidth(), background.getHeight()));
        btnPlay.setIcon(new Tools().getComponentIcon("res/play_button.png", btnPlay.getWidth(), btnPlay.getHeight()));
        btnBack.setIcon(new Tools().getComponentIcon("res/back_button.png", btnBack.getWidth(), btnBack.getHeight()));
        btnAdd.setIcon(new Tools().getComponentIcon("res/add_button.png", btnAdd.getWidth(), btnAdd.getHeight()));
        btnDelete.setIcon(new Tools().getComponentIcon("res/delete_button.png", btnDelete.getWidth(), btnDelete.getHeight()));

        this.user = user;
        
        cbTroops.removeAllItems();
        tbModel = (DefaultTableModel) troops.getModel();
        
        for (int i = 0; i < user.getAllCharacters().size(); i++){
            cbTroops.addItem(user.getAllCharacters().get(i).getName());
        }
        
        totalTroops = user.getTroops();
        posInTable = -1;
        
        lbUserLevel.setText("Jugador Nivel: " + user.getLevel());
        setTroopsText();
        setLocationRelativeTo(null);
    }

    private void setLabelImage(JLabel label, String path){
        try {
            label.setIcon(Tools.getComponentIcon(path, label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        troops = new javax.swing.JTable();
        btnBack = new javax.swing.JLabel();
        lbCantidadTrops = new javax.swing.JLabel();
        cbTroops = new javax.swing.JComboBox<>();
        btnPlay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbUserLevel = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(247, 247, 247));
        setMinimumSize(new java.awt.Dimension(800, 455));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 455));
        getContentPane().setLayout(null);

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 100));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        getContentPane().add(btnAdd);
        btnAdd.setBounds(30, 350, 120, 60);

        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setPreferredSize(new java.awt.Dimension(200, 100));
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
        btnDelete.setBounds(30, 270, 120, 60);

        troops.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Tropas", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        troops.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(troops);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(290, 50, 250, 360);

        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnBack.setBounds(640, 270, 120, 60);

        lbCantidadTrops.setBackground(new java.awt.Color(255, 255, 255));
        lbCantidadTrops.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCantidadTrops.setForeground(new java.awt.Color(255, 255, 255));
        lbCantidadTrops.setText("Tropas (current/max)");
        getContentPane().add(lbCantidadTrops);
        lbCantidadTrops.setBounds(30, 180, 210, 25);

        cbTroops.setForeground(new java.awt.Color(0, 0, 0));
        cbTroops.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTroops.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(cbTroops);
        cbTroops.setBounds(30, 90, 180, 30);

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
        btnPlay.setBounds(640, 350, 120, 60);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tropas");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(370, 0, 60, 25);

        lbUserLevel.setBackground(new java.awt.Color(255, 255, 255));
        lbUserLevel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbUserLevel.setForeground(new java.awt.Color(255, 255, 255));
        lbUserLevel.setText("Jugador Nivel: ");
        getContentPane().add(lbUserLevel);
        lbUserLevel.setBounds(30, 140, 180, 25);
        getContentPane().add(header);
        header.setBounds(0, 0, 800, 40);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Seleccionar tropa");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 50, 180, 25);
        getContentPane().add(background);
        background.setBounds(0, 0, 800, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        new MainWindow(user).setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        if (currentTroops != 0){ // iniciamos el game
            user.setToPlay(warriors);
            new GameWindow(user).setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Por favor, agregue tropas para jugar!");
        }
    }//GEN-LAST:event_btnPlayMouseClicked
    
    // Animaciones
    private void btnPlayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseEntered
        setLabelImage(btnPlay, "res/play_focus_button.png");
    }//GEN-LAST:event_btnPlayMouseEntered

    private void btnPlayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseExited
        setLabelImage(btnPlay, "res/play_button.png");
    }//GEN-LAST:event_btnPlayMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        setLabelImage(btnBack, "res/back_focus_button.png");
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        setLabelImage(btnBack, "res/back_button.png");
    }//GEN-LAST:event_btnBackMouseExited

    private int getWarrior(String name){
        int pos = 0;
        
        for (int i = 0; i < warriors.size(); i++){
            if (warriors.get(i).getName().equals(name)){
                pos = i;
                break;
            }
        }
        
        return pos;
    }
    
    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        posInTable = troops.getSelectedRow();
        
        if (posInTable != -1){
            int amountInTable = Integer.parseInt((String) tbModel.getValueAt(posInTable, 1));
            String name = (String) tbModel.getValueAt(posInTable, 0);
            
            int selectedTroop = getWarrior(name);
            int amount = warriors.get(selectedTroop).getTroops();
            
            currentTroops -= amount;
            setTroopsText();
            
            tbModel.removeRow(posInTable);
            warriors.remove(selectedTroop);
            
            amountInTable--;
            
            if (amountInTable != 0){
                String data[] = {name, String.valueOf(amountInTable)};
                
                tbModel.addRow(data);
            }
            posInTable = -1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona una tropa de la tabla!");
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        setLabelImage(btnDelete, "res/delete_focus_button.png");
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        setLabelImage(btnDelete, "res/delete_button.png");
    }//GEN-LAST:event_btnDeleteMouseExited
    
    private void setTroopsText(){
        lbCantidadTrops.setText("Tropas ("+currentTroops+"/" + totalTroops + ")");
    }
    
    private int isWarriorInTheTable(String name, String data[]){
        int positionInTable = -1;
        
        String currentName;
        String currentAmount;
        
        var tableData = tbModel.getDataVector();
        
        for (int i = 0; i < tableData.size(); i++){
            currentName = (String) tableData.get(i).get(0);
            currentAmount = (String) tableData.get(i).get(1).toString();
                    
            if (currentName.equals(name)){
                int amount = Integer.parseInt(currentAmount);
                amount++;
                data[1] = String.valueOf(amount);
                positionInTable = i;
            }
        }
        
        return positionInTable;
    }
    
    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        int undefined = -1;
        int selected = cbTroops.getSelectedIndex();
        
        if (selected == undefined) return;
        
        int toAdd = user.getAllCharacters().get(selected).getTroops();
        
        if (currentTroops + toAdd <= totalTroops){
            String name = user.getAllCharacters().get(selected).getName();
            String data[] = {name, String.valueOf(1)};
            
            int positionInTable = isWarriorInTheTable(name, data);
            
            if (positionInTable != undefined)
                tbModel.removeRow(positionInTable);
            
            tbModel.addRow(data);
            
            warriors.add(user.getAllCharacters().get(selected).clone(user.getAllCharacters().get(selected)));
            currentTroops += toAdd;
            setTroopsText();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Limite de tropas alcanzado");
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        setLabelImage(btnAdd, "res/add_focus_button.png");
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        setLabelImage(btnAdd, "res/add_button.png");
    }//GEN-LAST:event_btnAddMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnPlay;
    private javax.swing.JComboBox<String> cbTroops;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCantidadTrops;
    private javax.swing.JLabel lbUserLevel;
    private javax.swing.JTable troops;
    // End of variables declaration//GEN-END:variables
}

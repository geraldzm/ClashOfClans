package com.game;

import com.game.views.MainWindow;
import com.game.views.GameWindow;


public class main {
    public static void main(String args[]) {
        // Esta va a ser la ventana principal but we have to wait
        //new MainWindow().setVisible(true);
        new GameWindow().setVisible(true);
    }
}

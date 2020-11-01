package com.game.coc;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super("Prueba");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setSize(920,840);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new GameBoard(), BorderLayout.CENTER);

        setVisible(true);
    }
}

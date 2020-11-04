package com.game.views;


import com.game.model.Game;

import javax.swing.*;
import java.awt.*;

public class WindowGame {
    public WindowGame(int widht, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(widht, height));
        frame.setMaximumSize(new Dimension(widht, height));
        frame.setMinimumSize(new Dimension(widht, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}

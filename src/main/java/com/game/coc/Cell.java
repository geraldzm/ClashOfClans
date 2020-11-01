package com.game.coc;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Cell extends JLabel {
    private GameBoard board;
    public Cell(GameBoard board, float x, float y, Point point, Color c) {
        this.board = board;
        setOpaque(true);
        setBackground(c);
        setLayout(null);
        setSize(46,42);
        setLocation((int)x, (int)y);

        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean moved = false;
                do {
                    int vx = (Math.random() > 0.5? -1 : 1);
                    int vy = (Math.random() > 0.5? -1 : 1);
                    if (board.isAbailable(point.x + vx, point.y + vy)) {
                        if (!board.ocupate(point.x + vx, point.y + vy)) return;
                        board.freeSpace(point.x, point.y);
                        setLocation((point.x + vx) * 46, (point.y + vy) * 42);
                        point.y = point.y + vy;
                        point.x = point.x + vx;
                        moved = true;
                    }
                }while (!moved);
            }
        }, 2000, 3000);

    }

}

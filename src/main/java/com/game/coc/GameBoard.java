package com.game.coc;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameBoard extends JPanel {

    private boolean matrix[][];

    public GameBoard() {
        setSize(920,840);

        setLayout(null);

        matrix = new boolean[20][20];
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(j %2 ==0 && i %2 != 0) {
                    add(new Cell(this, i * 46, j * 42, new Point(i, j), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
                    matrix[i][j] = true;
                }
            }
        }

    }

    synchronized boolean isAbailable(int x, int y){
        if(x < 0 || y < 0 || x >= 20 || y >= 20) return false;
        return !(matrix[x][y]);
    }

    void freeSpace(int x, int y){
        matrix[x][y] = false;
    }

    boolean ocupate(int x, int y){
        if(!isAbailable(x, y)) return false;
        matrix[x][y] = true;
        return true;
    }

}

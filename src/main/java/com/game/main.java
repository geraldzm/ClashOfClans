package com.game;

import com.game.model.Character;
import com.game.model.Characters.ContactWarrior;
import com.game.model.Team;
import com.game.utils.Node;
import com.game.utils.ShortestPath;
import com.game.views.MainWindow;
import com.game.views.GameWindow;

import java.awt.*;
import java.util.Random;


public class main {
    public static void main(String args[]) {
        // Esta va a ser la ventana principal but we have to wait
        //new MainWindow().setVisible(true);
       // new GameWindow().setVisible(true);

        Character[][] board = new Character[20][20];

        board [0][5] = new ContactWarrior(0,0, Team.FRIEND, null);
        board [1][5] = new ContactWarrior(0,0, Team.FRIEND, null);
        board [3][5] = new ContactWarrior(0,0, Team.FRIEND, null);
        board [4][5] = new ContactWarrior(0,0, Team.FRIEND, null);

        Random random = new Random();
        for (int i = 0; i <= 20; i++) {

            board [random.nextInt(18)+2][random.nextInt(18)+2] = new ContactWarrior(0,0, Team.FRIEND, null);

        }


        Point from = new Point(0,0);
        Point to = new Point(19,19);
        int distance = 1;

        Node n = ShortestPath.getPath(board, from, to, distance);

        System.out.println(ShortestPath.iterations);
        printNodes(n);
        printMatrix(board, from, to);
    }

    public static void printNodes(Node n){
        if(n == null) return;
        System.out.println(n);
        printNodes(n.getChild());
    }

    public static void printMatrix(Character[][] board, Point f, Point t){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(f.x == i && f.y == j)stringBuilder.append(" A ");
                else if(t.x == i && t.y == j)stringBuilder.append(" B ");
                else if(board[i][j] != null)stringBuilder.append(" * ");
                else stringBuilder.append(" _ ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());

    }
}

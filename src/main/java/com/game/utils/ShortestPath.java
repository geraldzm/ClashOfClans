package com.game.utils;

import java.util.*;
import java.awt.Point;

import com.game.model.Character;

public class ShortestPath {

    private static void printHash(Character[][] board, HashSet<Point> h){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(board[i][j] != null)stringBuilder.append(" * ");
                else if(h.contains(new Point(j, i)))stringBuilder.append(" # ");
                else stringBuilder.append(" _ ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    public static int iterations = 0;
    public static Node getPath(Character[][] board, Point from, Point to, int distance){

        HashSet<Point> viewed = new HashSet<>(); // buffer de los nodos visitados

        Node rs = getPathA(board, from, to, distance, null, viewed);
       // printHash(board, viewed);
        return rs;
    }

    private static Node getPathA(Character[][] board, Point thisPoint, Point to, int distance, Node father, HashSet<Point> viewed){
        if ( thisPoint.y < 0 || thisPoint.y >= board.length) return null; // validamos el tamano
        if (thisPoint.x < 0 || thisPoint.x >= board[0].length) return null;
        iterations++;

        if(viewed.contains(thisPoint)) return null; // ya había sido visitado
        viewed.add(thisPoint);// lo visitamos

        //probamos si aqui hay alguien entonces lo tiramos para los 8 lados
        if(board[thisPoint.y][thisPoint.x] != null && viewed.size() > 1) {// si había alguien aqui, tiramos los 8 lados, viewed.size() > 1 para que no tome en cuenta el primero NOTA: LUEGO VALIDAR SI ERA UN ENEMIGO, SI LO ERA DE VOLVER A ESTE MAE Y CAMBIAR EL TARGET
            if(father == null) return null;
            Node aux;
            for (Point p : getLados(board, father.toPoint())){
                aux = getPathA(board, p, to, distance, father, viewed);
                if(aux != null) return aux;
            }
            return null;
        }

        Node current = new Node(thisPoint, father); // creamos este nodo en el que estamos
        if(Math.abs(to.x - thisPoint.x) <= distance && Math.abs(to.y - thisPoint.y) <= distance){
            //System.out.println("Founded");
            return current;
        } // si este es la ultima posicion de la distancia

        // si no era, tiramos los nodos a su tendencia
        Point trend = getTrend(thisPoint, to);

        Node str; // para retornar en cadena

        str = getPathA(board, new Point(thisPoint.x + trend.x, thisPoint.y), to, distance, current, viewed);
        if(str != null){
            current.setChild(str);
            return current;
        }
        str = getPathA(board, new Point(thisPoint.x + trend.x, thisPoint.y + trend.y), to, distance, current, viewed);
        if(str != null){
            current.setChild(str);
            return current;
        }
        str = getPathA(board, new Point(thisPoint.x, thisPoint.y + trend.y), to, distance, current, viewed);
        if(str != null){
            current.setChild(str);
            return current;
        }

        return null;
    }

    //retorna los 8 lados si estan vacios o si existen
    private static ArrayList<Point> getLados(Character[][] board, Point p){
        ArrayList<Point> points = new ArrayList<>();

        int i, j;
        int x, y;
        for(i=-1; i < 2; i++){ // 9 iteraciones
            y = p.y + i;
            for(j=-1; j < 2; j++) {
                if(i == 0 && j == 0) continue;

                if (y < 0 || y >= board.length) continue;

                x = p.x + j;
                if ( x < 0 || x >= board[0].length) continue;

                if(board[y][x] != null) continue;
                points.add(new Point(x, y));
                iterations++;

            }
        }

        return points;
    }

    // retorna la tendencia hacia ese punto
    private static Point getTrend(Point from, Point to){

        int xtrend = to.x - from.x; // sacamos las tendecias de los valores
        if(xtrend != 0) xtrend = xtrend > 0 ? 1: -1;

        int ytrend = to.y - from.y;
        if(ytrend != 0) ytrend = ytrend > 0 ? 1: -1;

        return new Point(xtrend, ytrend);
    }
        // end my version
}

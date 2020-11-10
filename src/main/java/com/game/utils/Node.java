package com.game.utils;

import java.awt.Point;

@lombok.Getter
@lombok.Setter
public class Node {
    private int x;
    private int y;
    private Node father;
    private Node child;


    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.father = null;
    }
    
    public Node(int x, int y, Node father){
        this.x = x;
        this.y = y;
        this.father = father;
    }

    public Node(int x, int y, Node father, Node child) {
        this.x = x;
        this.y = y;
        this.father = father;
        this.child = child;
    }

    public Node(Point point, Node father, Node child) {
        this.x = point.x;
        this.y = point.y;
        this.father = father;
        this.child = child;
    }

    public Node(Point point, Node father) {
        this.x = point.x;
        this.y = point.y;
        this.father = father;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Point toPoint(){
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

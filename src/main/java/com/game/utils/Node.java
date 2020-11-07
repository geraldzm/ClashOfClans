package com.game.utils;

import java.awt.Point;

@lombok.Getter
@lombok.Setter
public class Node {
    private int x;
    private int y;
    private Node father;
    
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
    
    public Point toPoint(){
        return new Point(x, y);
    }
}

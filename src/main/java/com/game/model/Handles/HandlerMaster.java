package com.game.model.Handles;

import java.util.ArrayList;

public abstract class HandlerMaster<Type>{

    private ArrayList<Type> objectsList;

    public HandlerMaster() {
        this.objectsList = new ArrayList<>();
    }

    public HandlerMaster(ArrayList<Type> objectsList) {
        this.objectsList = objectsList;
    }

    public void addObject(Type object){
        objectsList.add(object);
    }

    public  void removeObject(Type object) {
        objectsList.remove(object);
    }

    public ArrayList<Type> getList() {
        return objectsList;
    }

    public void setObjectsList(ArrayList<Type> objectsList) {
        this.objectsList = objectsList;
    }
}

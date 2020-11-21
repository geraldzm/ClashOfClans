package com.game.model.Handles;

import com.game.model.Character;

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
        System.out.println("Nuevo tamano de lista: " + objectsList.size());
    }

    public ArrayList<Type> getList() {
        return objectsList;
    }

    public void setObjectsList(ArrayList<Type> objectsList) {
        this.objectsList = objectsList;
    }

    public <T extends Type> void addObjectsList(ArrayList<T> objectsList) {
        for (int i = 0; i < objectsList.size(); i++)
            this.objectsList.add(objectsList.get(i));
    }

}

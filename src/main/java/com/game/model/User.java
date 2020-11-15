package com.game.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;
    private String password;
    private int level;
    private ArrayList<Warrior> characters; // el nivel de sus caracteres
    private ArrayList<Warrior> allCharacters;

    // partida/veremos


    /**
     * <h1>Nuevo usuario</h1>
     * */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        level = 1;
        characters  = new ArrayList<>();
        allCharacters = new ArrayList<>();
    }


    //setters/getters
    public ArrayList<Warrior> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Warrior> characters) {
        this.characters = characters;
    }

    public ArrayList<Warrior> getAllCharacters() {
        return allCharacters;
    }

    public void setAllCharacters(ArrayList<Warrior> allCharacters) {
        this.allCharacters = allCharacters;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level);

        allCharacters.forEach(w -> s.append("\n").append(w));

         return s.toString();
    }
}

package com.game.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User implements Serializable {

    private String name;
    private String password;

    private int level;
    private int troops;

    private ArrayList<Warrior> characters; // el nivel de sus caracteres
    private ArrayList<Warrior> toPlay; // con los que va a jugar
    private ArrayList<Warrior> allCharacters;

    /**
     * <h1>Nuevo usuario</h1>
     * */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.troops = 5;
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

    /**
     * Esto retorna lo que se debe mostrar en el combobox
     *
     * */
    public List<String> getCharactersNames(int level){
        new File("").exists();
        return allCharacters.stream()
                .filter(w -> w.getAppearanceLevel() <= level)
                .map(Character::getName).collect(Collectors.toList());
    }

    /**
     * Esto retorna esa cantidad de warriors clonados, null si el nombre no existe
     *
     * */
    public ArrayList<Warrior> getClonedWarriors(String name, int amount){
        Warrior warrior = getWarriorByName(name);
        ArrayList<Warrior> warriors = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            warriors.add(warrior.clone(warrior));

        return warriors;
    }

    /*
     *  Se usa al crear un character
     * */
    public void addCreatedCharacter(Warrior warrior){
        this.allCharacters.add(warrior);
        this.characters.add(warrior.clone(warrior));
    }

    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return level;
    }

    public void levelUp() {
        this.level++;
    }

    public int getTroops() {
        return troops + (level-1) * 3;
    }
    
    public boolean login(String pass){
        return password.equals(pass);
    }

    public ArrayList<Warrior> getToPlay() {
        return toPlay;
    }

    public void setToPlay(ArrayList<Warrior> toPlay) {
        this.toPlay = toPlay;
    }

    public Warrior getWarriorByName(String name){
        for (Warrior character : characters) {
            if (name.equals(character.getName())) {
                return character;
            }
        }

        return null;
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

package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cave implements Serializable {
    private Player player;
    private List<Monster> monsters;
    
    public Cave(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
    }
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }
    public void listMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Luola on tyhjä.");
        }
        else {
            System.out.println("Luolan hirviöt:");
            for (int i = 0; i < monsters.size(); i++) {
                monsters.get(i).printInfo(i + 1);
            }
        }
    }
    public Player getPlayer() {
        return player;
    }
    public void attackMonster(int choice) {
        Monster monster = monsters.get(choice - 1);
        player.attack(monster);
        if (monster.getHealth() <= 0) {
            monsters.remove(choice - 1);
        }
    }
    public void saveGame(String filename) {
        try {
            ObjectOutputStream gameWriter = new ObjectOutputStream(new FileOutputStream(filename));
            gameWriter.writeObject(monsters);
            gameWriter.close();
            System.out.println("Peli tallennettiin tiedostoon " + filename + ".");
        }
        catch (IOException e) {
            System.out.println("Virhe talentaessa peliä.");
        }
        
    }
    public void loadGame(String filename) {
        try {
            ObjectInputStream gameReader = new ObjectInputStream(new FileInputStream(filename));
            monsters = (List<Monster>) gameReader.readObject();
            gameReader.close();
            System.out.println("Peli ladattu tiedostosta " + filename + ". Tervetuloa takaisin, " + player.getName() + ".");
        }
        catch (IOException e) {
            System.out.println("Virhe ladatessa peliä.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Virhe ladatessa peliä.");
        }
    }
}


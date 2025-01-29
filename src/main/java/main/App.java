package main;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Syötä pelaajan nimi:");
        Player player = new Player(sc.nextLine());
        Cave cave = new Cave(player);

        boolean exit = false;
        while(!exit) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");

            try {
                int i = Integer.parseInt(sc.nextLine());

                switch(i) {
                    case 1:
                        System.out.println("Anna hirviön tyyppi:");
                        String type = sc.nextLine();
                        System.out.println("Anna hirviön elämän määrä numerona:");
                        int health = Integer.parseInt(sc.nextLine());
                        cave.addMonster(new Monster(type, health));
                        break;

                    case 2:
                        System.out.println("Luolan hirviöt:");
                        cave.listMonsters();
                        break;
                    case 3:
                        System.out.println("Valitse hirviö, johon hyökätä:");
                        cave.listMonsters();
                        int choice = Integer.parseInt(sc.nextLine());
                        cave.attackMonster(choice);
                        break;
                    case 4:
                        System.out.println("Anna tiedoston nimi, johon peli tallentaa:");
                        String saveFilename = sc.nextLine();
                        cave.saveGame(saveFilename);
                        break;
                    case 5:
                        System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                        String loadFilename = sc.nextLine();
                        cave.loadGame(loadFilename);
                        break;
                    case 0:
                        System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Syöte oli väärä.");
                        break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Väärä syöte.");

            }
        }
        sc.close();
    }
}

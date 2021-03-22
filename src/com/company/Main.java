package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(">>>>> GANZEBORD <<<<<<<<");
        BordSpel ganzenbord = new BordSpel();
        ganzenbord.nieuwSpel();
        ganzenbord.spel();
    }
}

class BordSpel {
    public int huidigePlaats = 0;
    public final int finishVeld = 63;
    public int aantalOgen;
    public final Random random = new Random();

    public void nieuwSpel() {
        huidigePlaats = 0;
        System.out.println("Je staat op start.");
    }

    public int dobbelsteenWorp() {
        aantalOgen = random.nextInt(6) + 1; //om 1 t/m 6 te krijgen ipv 0 t/m 5.
        return aantalOgen;
    }

    public void checkVeld(int veld) {
        if (veld == 10 || veld == 20 || veld == 30 || veld == 40 || veld == 50 || veld == 60) {
            huidigePlaats += aantalOgen;
            System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Je staat op plaats " + huidigePlaats);
        } else if (veld == 25 || veld == 45) {
            huidigePlaats = 0;
            System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ". Terug naar start.");
        } else {
            System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ".");
        }
    }

    public void spel() {
        Scanner scan = new Scanner(System.in);
        while (huidigePlaats < finishVeld) {
            System.out.println("\nGooi je dobbelsteen (g).");
            String input = scan.next();

            if (input.equals("g")) {
                dobbelsteenWorp();
                huidigePlaats += aantalOgen;
            }
            checkVeld(huidigePlaats);
        }

        if (huidigePlaats == 23) {
            System.out.println("Gevangenis! GAME OVER! :-( ");
        }

        if (huidigePlaats >= finishVeld) {
            System.out.println("Gewonnen!");
        }
    }

}

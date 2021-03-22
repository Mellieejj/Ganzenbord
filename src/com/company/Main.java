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
    public final int finishVeld = 10;
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

    public void spel() {
        try(Scanner scan = new Scanner(System.in)){
            while(huidigePlaats < finishVeld) {
                System.out.println("\nGooi je dobbelsteen (g).");
                String input = scan.next();
                if (input.equals("g")) {
                    dobbelsteenWorp();
                    huidigePlaats += aantalOgen;
                }

                System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + huidigePlaats);
            }
        }

        if (huidigePlaats >= finishVeld){
            System.out.println("Gewonnen!");
        }
    }

}

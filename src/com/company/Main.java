package com.company;

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
    Dobbelsteen dobbelsteen = new Dobbelsteen();
    public int aantalOgen;

    public void nieuwSpel() {
        huidigePlaats = 0;
        System.out.println("Je staat op start.");
    }

    public void achteruit(boolean bonusStapjes) {
        if (!bonusStapjes) {
            huidigePlaats = finishVeld - (huidigePlaats - finishVeld);
            System.out.println("Je hebt " + aantalOgen + " gegooid. Ojee, dat is over de " + finishVeld + ", in zijn achteruit! Je staat op plaats " + huidigePlaats);
        }
    }

    public void achteruit(boolean bonusStapjes, int veld) {
        huidigePlaats = finishVeld - (huidigePlaats - finishVeld);
        if (bonusStapjes) {
            System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Ojee dat is over de " + finishVeld + ", je staat op plaats " + huidigePlaats);
        }
    }

    public void checkVeld(int veld) {
        if (veld == 10 || veld == 20 || veld == 30 || veld == 40 || veld == 50 || veld == 60) {
            huidigePlaats += aantalOgen;
            if (huidigePlaats > finishVeld) {
                achteruit(true, veld);
            } else {
                System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Je staat op plaats " + huidigePlaats);
            }
        } else if (veld == 25 || veld == 45) {
            huidigePlaats = 0;
            System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ". Terug naar start.");
        } else {
            if (huidigePlaats > finishVeld) {
                achteruit(false);
            } else {
                System.out.println("Je hebt " + aantalOgen + " gegooid. Je staat op plaats " + veld + ".");
            }
        }
    }

    public void spel() {
        Scanner scan = new Scanner(System.in);
        while (huidigePlaats < finishVeld) {
            System.out.println("\nGooi je dobbelsteen (g).");
            String input = scan.next();

            if (input.equals("g")) {
               aantalOgen = dobbelsteen.gooien();
                huidigePlaats += aantalOgen;
            }
            checkVeld(huidigePlaats);

            if (huidigePlaats == 23) {
                System.out.println("Gevangenis! GAME OVER! :-( ");
                break;
            }
        }

        if (huidigePlaats == finishVeld) {
            System.out.println("Gewonnen!");
        }
    }

}

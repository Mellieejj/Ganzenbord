package com.company;

import java.util.ArrayList;
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
    Scanner scan = new Scanner(System.in);
    public final int finishVeld = 63;
    boolean afgelopen = false;
    Dobbelsteen dobbelsteen = new Dobbelsteen();
    ArrayList<Speler> spelers = new ArrayList<Speler>();

    public void nieuwSpel() {
        System.out.println("Hoeveel spelers doen er mee?");
        int aantalSpelers = Integer.valueOf(scan.nextLine());

        for (int i = 0; i < aantalSpelers; i++ ){
            System.out.println("Hoe heet speler " + (i+1) + "?");
            spelers.add(new Speler(scan.nextLine()));
        }

        for (Speler speler : spelers) {
            System.out.println(speler.naam + " staat op: " + speler.huidigePlek);
        }

        System.out.println("Alle spelers staan op start.");
    }

    public void achteruit(Speler speler, boolean bonusStapjes) {
        if (!bonusStapjes) {
            speler.huidigePlek = finishVeld - (speler.huidigePlek - finishVeld);
            System.out.println("Je hebt " + speler.laatsteWorp + " gegooid. Ojee, dat is over de " + finishVeld + ", in zijn achteruit! Je staat op plaats " + speler.huidigePlek + ".");
        }
    }

    public void achteruit(Speler speler, boolean bonusStapjes, int veld) {
        speler.huidigePlek = finishVeld - (speler.huidigePlek - finishVeld);
        if (bonusStapjes) {
            System.out.println("Je hebt " + speler.laatsteWorp + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Ojee dat is over de " + finishVeld + ", je staat op plaats " + speler.huidigePlek + ".");
        }
    }

    public void checkVeld(Speler speler) {
        int ogen = speler.laatsteWorp;
        int veld = speler.huidigePlek;

        if (veld == 6) {
            speler.huidigePlek = 12;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Hee een brug! Oversteken naar 12!");
        } else if (veld == 10 || veld == 20 || veld == 30 || veld == 40 || veld == 50 || veld == 60) {
            speler.huidigePlek += ogen;
            if (speler.huidigePlek > finishVeld) {
                achteruit(speler, true, veld);
            } else {
                System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Je staat op plaats " + speler.huidigePlek + ".");
            }
        } else if (veld == 42) {
            speler.huidigePlek = 39;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Doolhof! Terug naar 39.");
        } else if (veld == 58) {
            speler.huidigePlek = 0;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + "Dood.... Terug naar start.");
        } else {
            if (speler.huidigePlek > finishVeld) {
                achteruit(speler, false);
            } else {
                System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ".");
            }
        }
    }

    public void spel() {


        while (afgelopen != true) {
            for (Speler speler : spelers) {

                System.out.println("\n" + speler.naam + " gooi de dobbelsteen (g).");
                String input = scan.next();
                if (input.equals("g")) {
                    speler.laatsteWorp = dobbelsteen.gooien();
                    speler.huidigePlek += speler.laatsteWorp;
                }
                checkVeld(speler);

                System.out.println("Je beurt is voorbij.");

                if (speler.huidigePlek == 63) {
                    afgelopen = true;
                    System.out.println(speler.naam + " heeft gewonnen.");
                    break;
                }
            }
            if (afgelopen) {
                break;
            }
        }
    }
}

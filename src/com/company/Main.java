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
    public final int finishVeld = 63;
    boolean afgelopen = false;
    Dobbelsteen dobbelsteen = new Dobbelsteen();
    Speler speler1 = new Speler("Melanie");
    Speler speler2 = new Speler("Martin");

    Speler[] spelers = {speler1, speler2};

    public void nieuwSpel() {
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
        if (veld == 10 || veld == 20 || veld == 30 || veld == 40 || veld == 50 || veld == 60) {
            speler.huidigePlek += ogen;
            if (speler.huidigePlek > finishVeld) {
                achteruit(speler, true, veld);
            } else {
                System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". BONUS STAPJES! Je staat op plaats " + speler.huidigePlek + ".");
            }
        } else if (veld == 25 || veld == 45) {
            speler.huidigePlek = 0;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Terug naar start.");
        } else {
            if (speler.huidigePlek > finishVeld) {
                achteruit(speler, false);
            } else {
                System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ".");
            }
        }
    }

    public void spel() {
        Scanner scan = new Scanner(System.in);

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

//            if (huidigePlaats == 23) {
//                System.out.println("Gevangenis! GAME OVER! :-( ");
//                break;
//            }
        }
    }
}

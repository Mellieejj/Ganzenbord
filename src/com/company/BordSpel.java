package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BordSpel {
    Scanner scan = new Scanner(System.in);
    private final int finishVeld = 63;
    private boolean afgelopen = false;
    Dobbelsteen dobbelsteen = new Dobbelsteen();
    ArrayList<Speler> spelers = new ArrayList<>();

    public void nieuwSpel() {
        System.out.println("Hoeveel spelers doen er mee?");
        int aantalSpelers = Integer.valueOf(scan.nextLine());

        for (int i = 0; i < aantalSpelers; i++) {
            System.out.println("Hoe heet speler " + (i + 1) + "?");
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
        } else if (veld == 19) {
            speler.herberg = true;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Herberg. 1 beurt overslaan.");
        } else if (veld == 31) {
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Je zit in de put, je kunt pas verder als er een andere speler voorbij komt of in de put beland.");
        } else if (veld == 42) {
            speler.huidigePlek = 39;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Doolhof! Terug naar 39.");
        } else if (veld == 52) {
            speler.gevangenisCounter = 3;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". GEVANGENIS! Sla 3 beurten over.");
        } else if (veld == 58) {
            speler.huidigePlek = 0;
            System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ". Dood.... Terug naar start.");
        } else {
            if (speler.huidigePlek > finishVeld) {
                achteruit(speler, false);
            } else {
                System.out.println("Je hebt " + ogen + " gegooid. Je staat op plaats " + veld + ".");
            }
        }
    }

    public void beurt(Speler speler) {
        System.out.println("\n" + speler.naam + " gooi de dobbelsteen (g).");
        String input = scan.next();

        if (input.equals("g")) {
            speler.laatsteWorp = dobbelsteen.gooien();
            speler.huidigePlek += speler.laatsteWorp;
            checkVeld(speler);
            System.out.println("Je beurt is voorbij.");
        } else {
            System.out.println("Whoops, de dobbelsteen viel op de grond, probeer nog eens. \uD83D\uDE1C");
            beurt(speler);
        }
    }

    public void spel() {
        while (!afgelopen) {
            for (Speler speler : spelers) {
                speler.oudePlek = speler.huidigePlek;

                if (speler.herberg) {
                    System.out.println("\n" + speler.naam + ", je moet deze beurt overslaan.");
                    speler.herberg = false;
                } else if (speler.huidigePlek == 31) {
                    for (Speler sp : spelers) {
                        if (sp != speler) {
                            if (sp.oudePlek < 31 && sp.huidigePlek >= 31) {
                                System.out.println(speler.naam + ", je mag uit de put! " + sp.naam + " is je gepasseerd!");
                                beurt(speler);
                                break;
                            } else {
                                System.out.println("Helaas, " + sp.naam + " is niet voorbij jou gekomen.");
                            }
                        }
                    }
                } else if (speler.huidigePlek == 52) {
                    if (speler.gevangenisCounter == 0) {
                        System.out.println("counter is 0");
                        beurt(speler);
                    } else {
                        speler.gevangenisCounter--;
                        System.out.println("\n" + speler.naam + ", je moet nog " + speler.gevangenisCounter + " beurt(en) overslaan.");
                    }
                } else {
                    beurt(speler);
                }

                if (speler.huidigePlek == 63) {
                    afgelopen = true;
                    System.out.println(speler.naam + " heeft gewonnen.");
                    break;
                }
            }
        }
    }
}

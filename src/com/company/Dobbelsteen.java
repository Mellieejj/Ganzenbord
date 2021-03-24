package com.company;

import java.util.Random;

public class Dobbelsteen {
    private final Random random = new Random();
    public int gooien() {
        return random.nextInt(6) + 1;
    }
}

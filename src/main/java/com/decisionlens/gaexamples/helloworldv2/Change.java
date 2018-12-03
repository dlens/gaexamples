package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.operators.ListOrderCrossover;

import java.util.Random;

public class Change {
    private static char[] LETTERS = Alphabet.create();
    private char letter;
    private int position;

    public Change(int position, Random random) {
        this.position = position;
        this.letter = LETTERS[random.nextInt(LETTERS.length)];
    }

    public char getLetter() {
        return letter;
    }

    public Change setLetter(char letter) {
        this.letter = letter;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return "(" + position + ", letter=" + letter + ")";
    }
}

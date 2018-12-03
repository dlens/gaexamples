package com.decisionlens.gaexamples.helloworldv2;

public class Alphabet {

    public static char[] create() {
        char[] alphabet = new char[27];
        for(char c = 'A'; c <= 'Z'; c++) {
            alphabet[c - 'A'] = c;
        }
        alphabet[26] = ' ';
        return alphabet;
    }
}

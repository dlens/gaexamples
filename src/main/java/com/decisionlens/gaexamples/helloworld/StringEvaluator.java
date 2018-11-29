package com.decisionlens.gaexamples.helloworld;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class StringEvaluator implements FitnessEvaluator<String> {
    private final String targetString = "HELLO WORLD";

    public double getFitness(String candidate, List<? extends String> population) {
        int matches = 0;
        for(int i = 0; i < candidate.length(); i++) {
            if(candidate.charAt(i) == targetString.charAt(i)) {
                ++matches;
            }
        }
        return matches;
    }

    public boolean isNatural() {
        return true;
    }
}

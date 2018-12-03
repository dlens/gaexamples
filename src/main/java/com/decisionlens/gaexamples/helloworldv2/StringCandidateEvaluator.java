package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class StringCandidateEvaluator implements FitnessEvaluator<StringCandidate> {
    private final String targetString = "HELLO WORLD";

    @Override
    public double getFitness(StringCandidate candidate, List<? extends StringCandidate> population) {
        int matches = 0;
        for(int i = 0; i < candidate.getCandidate().length(); i++) {
            if(candidate.getCandidate().charAt(i) == targetString.charAt(i)) {
                ++matches;
            }
        }
        return matches;
    }

    public boolean isNatural() {
        return true;
    }
}

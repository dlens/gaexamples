package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import org.uncommons.watchmaker.framework.operators.StringCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringCandidateCrossover extends AbstractCrossover<StringCandidate> {

    public StringCandidateCrossover() {
        this(1);
    }

    protected StringCandidateCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    @Override
    protected List<StringCandidate> mate(StringCandidate parent1, StringCandidate parent2, int numberOfCrossoverPoints, Random random) {
        StringBuilder offspring1 = new StringBuilder(parent1.getCandidate());
        StringBuilder offspring2 = new StringBuilder(parent2.getCandidate());
        for (int i = 0; i < numberOfCrossoverPoints; i++) {
            int crossoverIndex = (1 + random.nextInt(parent1.getCandidate().length() - 1));
            for (int j = 0; j < crossoverIndex; j++) {
                char temp = offspring1.charAt(j);
                offspring1.setCharAt(j, offspring2.charAt(j));
                offspring2.setCharAt(j, temp);
            }
        }
        List<StringCandidate> result = new ArrayList<>(2);
        result.add(new StringCandidate().setCandidate(offspring1.toString()));
        result.add(new StringCandidate().setCandidate(offspring2.toString()));
        return result;
    }
}

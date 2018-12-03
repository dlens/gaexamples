package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class StringCandidateCrossover extends AbstractCrossover<StringCandidate> {


    protected StringCandidateCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    @Override
    protected List<StringCandidate> mate(StringCandidate parent1, StringCandidate parent2, int numberOfCrossoverPoints, Random rng) {
        int parent1CrossoverIndex = rng.nextInt(parent1.size());
        int parent2CrossoverIndex = rng.nextInt(parent2.size());

        int length1 = parent1CrossoverIndex;

    }


}

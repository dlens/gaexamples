package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class StringCandidateCrossover extends AbstractCrossover<StringCandidate> {


    private static double ProbabilityOfCrossover = 0.2;

    StringCandidateCrossover(Probability probabilityOfCrossover) {
        super(2, probabilityOfCrossover);
        ProbabilityOfCrossover = probabilityOfCrossover.doubleValue();
    }

    @Override
    protected List<StringCandidate> mate(StringCandidate parent1, StringCandidate parent2, int numberOfCrossoverPoints, Random rng) {
        if ((parent1.size() < 1) || (parent2.size() < 1) || (rng.nextDouble() > ProbabilityOfCrossover)) {
            StringCandidate rval1 = new StringCandidate(parent1);
            StringCandidate rval2 = new StringCandidate(parent2);
            List<StringCandidate> rval = new ArrayList<>(2);
            rval.add(rval1);
            rval.add(rval2);
            return rval;
        }
        int parent1CrossoverIndex = rng.nextInt(parent1.size());
        int parent2CrossoverIndex = rng.nextInt(parent2.size());

        int p1EndSize = parent1.size() - parent1CrossoverIndex;
        int p2EndSize = parent2.size() - parent2CrossoverIndex;

        int length1 = parent1CrossoverIndex + p2EndSize;
        int length2 = parent2CrossoverIndex + p1EndSize;

        StringCandidate rval1 = new StringCandidate(parent1.getInitialString());
        for(int i=0; i < parent1CrossoverIndex; i++)
            rval1.add(parent1.get(i));
        for(int i=parent2CrossoverIndex; i < parent2.size(); i++)
            rval1.add(parent2.get(i));

        StringCandidate rval2 = new StringCandidate(parent2.getInitialString());
        for(int i=0; i < parent2CrossoverIndex; i++)
            rval1.add(parent2.get(i));
        for(int i=parent1CrossoverIndex; i < parent1.size(); i++)
            rval1.add(parent1.get(i));
        List<StringCandidate> rval = new ArrayList<>(2);
        rval.add(rval1);
        rval.add(rval2);
        return rval;
    }


}

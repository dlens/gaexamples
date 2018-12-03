package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringCandidateMutation implements EvolutionaryOperator<StringCandidate> {
    private static final double PROPB_DELETE = 0.25;
    private final char[] alphabet = Alphabet.create();
    private final NumberGenerator<Probability> mutationProbability;

    public StringCandidateMutation(Probability mutationProbability) {
        this.mutationProbability = new ConstantGenerator<>(mutationProbability);
    }

    @Override
    public List<StringCandidate> apply(List<StringCandidate> selectedCandidates, Random rng) {
        List<StringCandidate> mutatedPopulation = new ArrayList<>(selectedCandidates.size());
        for (StringCandidate s : selectedCandidates) {
            mutatedPopulation.add(mutate(s, rng));
        }
        return mutatedPopulation;
    }

    private StringCandidate mutate(StringCandidate s, Random rng) {
      if(rng.nextDouble() < PROPB_DELETE) {
          return mutateDelete(s, rng);
      } else {
          return mutateAdd(s, rng);
      }
    }

    private StringCandidate mutateAdd(StringCandidate s, Random rng) {
        s = new StringCandidate(s);
        int position = rng.nextInt(s.getInitialString().length());
        s.add(new Change(position, rng));
        return s;
    }

    private StringCandidate mutateDelete(StringCandidate s, Random rng) {
        s = new StringCandidate(s);
        if(s.size() < 1) {
            return s;
        }

        int position = rng.nextInt(s.size());
        s.remove(position);
        return s;
    }
}

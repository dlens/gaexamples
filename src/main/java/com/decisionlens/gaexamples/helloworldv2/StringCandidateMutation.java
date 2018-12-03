package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringCandidateMutation implements EvolutionaryOperator<StringCandidate> {
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
        StringBuilder buffer = new StringBuilder(s.getCandidate());
        for (int i = 0; i < buffer.length(); i++) {
            if (mutationProbability.nextValue().nextEvent(rng)) {
                buffer.setCharAt(i, alphabet[rng.nextInt(alphabet.length)]);
            }
        }
        return new StringCandidate().setCandidate(buffer.toString());
    }
}

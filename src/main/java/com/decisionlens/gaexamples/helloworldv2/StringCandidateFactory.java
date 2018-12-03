package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class StringCandidateFactory extends AbstractCandidateFactory<StringCandidate> {
    private final char[] alphabet = Alphabet.create();
    private int targetStringLength;

    public StringCandidateFactory(int targetStringLength) {
        this.targetStringLength = targetStringLength;
    }

    @Override
    public StringCandidate generateRandomCandidate(Random random) {
        StringCandidate stringCandidate = new StringCandidate();
        for (int i = 0; i < targetStringLength; i++) {
            char letter = alphabet[random.nextInt(alphabet.length)];
            stringCandidate.addChange(new Change().setLetter(letter));
        }
        return stringCandidate;
    }
}

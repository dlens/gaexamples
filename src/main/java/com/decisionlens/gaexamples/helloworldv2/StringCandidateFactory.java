package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class StringCandidateFactory extends AbstractCandidateFactory<StringCandidate> {
    private String initialString;
    private int numChanges = 5;

    public StringCandidateFactory(String initialString) {
        this.initialString = initialString;
    }

    @Override
    public StringCandidate generateRandomCandidate(Random random) {
        StringCandidate stringCandidate = new StringCandidate(initialString);
        for(int i = 0; i < numChanges; i++) {
            stringCandidate.add(new Change(random.nextInt(initialString.length()), random));
        }

        return stringCandidate;
    }
}

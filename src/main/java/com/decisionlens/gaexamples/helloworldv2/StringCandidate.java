package com.decisionlens.gaexamples.helloworldv2;

import java.util.ArrayList;

public class StringCandidate extends ArrayList<Change> {
    private String initialString;

    public StringCandidate(String initialString) {
        this.initialString = initialString;
    }

    public StringCandidate(StringCandidate s) {
        super(s);
        this.initialString = s.initialString;
    }

    public String getInitialString() {
        return initialString;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }

    public String getCandidate() {
        StringBuilder candidate = new StringBuilder(initialString);
        this.forEach(change -> candidate.setCharAt(change.getPosition(), change.getLetter()));
        return candidate.toString();
    }
}

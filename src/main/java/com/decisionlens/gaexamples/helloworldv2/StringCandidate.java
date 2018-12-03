package com.decisionlens.gaexamples.helloworldv2;

import java.util.ArrayList;

public class StringCandidate extends ArrayList<Change> {
    private String initialString;

    public StringCandidate(String initialString) {
        this.initialString = initialString;
    }

    public StringCandidate(StringCandidate s) {
        super();
        for(Change c: s) {
            add(new Change(c));
        }
        this.initialString = s.initialString;
    }

    public String getInitialString() {
        return initialString;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }

    @Override
    public boolean add(Change change) {
        //See if a change like this already exists?
        for(Change ch : this) {
            if (ch.getPosition() == change.getPosition()) {
                //We already have a change like this, reset
                ch.setLetter(change.getLetter());
                return true;
            }
        }
        //No such change yet
        return super.add(change);
    }

    public String getCandidate() {
        StringBuilder candidate = new StringBuilder(initialString);
        this.forEach(change -> candidate.setCharAt(change.getPosition(), change.getLetter()));
        return candidate.toString();
    }
}

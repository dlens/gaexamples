package com.decisionlens.gaexamples.helloworldv2;

import java.util.ArrayList;
import java.util.List;

public class StringCandidate {
    private List<Change> changes = new ArrayList<>();

    public String getCandidate() {
        StringBuilder builder = new StringBuilder();
        changes.forEach(change -> builder.append(change.getLetter()));
        return builder.toString();
    }

    public StringCandidate setCandidate(String candidate) {
        this.changes.clear();
        for (int i = 0; i < candidate.length(); i++) {
            addChange(new Change().setLetter(candidate.charAt(i)));
        }
        return this;
    }

    public void addChange(Change change) {
        this.changes.add(change);
    }
}

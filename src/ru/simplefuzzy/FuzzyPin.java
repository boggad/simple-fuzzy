package ru.simplefuzzy;

import java.util.Vector;

/**
 * Copyright (c) 2015 Timofey Klyubin, The MIT License
 */

public class FuzzyPin {
    public Vector<FuzzyTerm> terms;
    public double value;

    public FuzzyPin(Vector<FuzzyTerm> terms) {
        this.terms = terms;
    }

    public FuzzyPin() {
        this.terms = new Vector<FuzzyTerm>();
    }
}

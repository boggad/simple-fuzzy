package ru.simplefuzzy;

import java.util.Vector;

/**
 * Created by Тимофей on 04.05.2015.
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

package ru.simplefuzzy;

import java.util.Vector;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Timofey Klyubin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

public class Fuzzy {
    private Vector<FuzzyPin> inputs;
    private FuzzyPin output;
    private Vector<FuzzyRule> rules;

    public Fuzzy(FuzzyPin output) {
        this.inputs = new Vector<FuzzyPin>();
        this.rules = new Vector<FuzzyRule>();
        this.output = output;
    }

    public void addInput(FuzzyPin input) {
        inputs.add(input);
    }

    public void addRule(FuzzyRule rule) {
        rules.add(rule);
    }

    private void fuzzification() {
        // агрегирование проводится здесь же
        for(int i = 0; i < inputs.size(); i++) {
            for(int t = 0; t < inputs.get(i).terms.size(); t++) {
                FuzzyTerm term = inputs.get(i).terms.get(t);
                term.value = term.getFunction().func(inputs.get(i).value);
            }
        }
    }

    private void activate() {
        for(int i = 0; i < rules.size(); i++) {
            rules.get(i).activate();
        }
    }

    public double output() {
        fuzzification();
        activate();
        double numerator = 0, denominator = 0;
        for(int i = 0; i < rules.size(); i++) {
            FuzzyTerm o = rules.get(i).outTerm;
            double v = rules.get(i).out;
            denominator += v;
            numerator += o.getFunction().getMedian()*v;
        }
        return numerator/denominator;
    }
}

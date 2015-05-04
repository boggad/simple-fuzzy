package ru.simplefuzzy;

import java.util.Vector;

/**
 * Created by Тимофей on 04.05.2015.
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

package ru.simplefuzzy;

/**
 * Copyright (c) 2015 Timofey Klyubin, The MIT License
 */

public class FuzzyTerm {
    private FuzzyFunc function;
    public double value;;

    public FuzzyTerm(FuzzyFunc function) {
        this.function = function;
    }

    public FuzzyTerm(FuzzyFunc function, double value) {
        this.function = function;
        this.value = value;
    }

    public FuzzyFunc getFunction() {
        return function;
    }

    public void setFunction(FuzzyFunc function) {
        this.function = function;
    }
}

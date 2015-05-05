package ru.simplefuzzy;

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

/**
 * Describes a term.
 *
 * <p>
 *     This class is actually a wrapper for <code>FuzzyFunc</code>. It contains one additional variable; this class
 *     helps to keep semantic, and may be useful for future improvements.
 * </p>
 *
 * @author Timofey
 * @see ru.simplefuzzy.Fuzzy {@link ru.simplefuzzy.Fuzzy}
 * @see ru.simplefuzzy.FuzzyFunc {@link ru.simplefuzzy.FuzzyTerm}
 * @since 0.1
 * @version 0.1
 */

public class FuzzyTerm {
    private FuzzyFunc function;
    /**
     * Stores a value given during fuzzification.
     */
    public double value;

    /**
     * Standard constructor.
     * @param function determines a membership function associated with this term
     * @since 0.1
     */
    public FuzzyTerm(FuzzyFunc function) {
        this.function = function;
    }

    /**
     * Constructor wih default value.
     * @param function determines a membership function associated with this term
     * @param value determines default value
     * @since 0.1
     */
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

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

/**
 * Defines a set of terms.
 *
 * <p>
 *     <code>FuzzyPin</code> describes either one input signal or one output signal. Pin is a set of terms associated
 *     with this pin. For example, if you need to open a shutter depending on air temperature and time, you would have
 *     one <code>FuzzyPin</code> as input for air pressure, one <code>FuzzyPin</code> as input for time and one
 *     <code>FuzzyPin</code> as output for shutter's angle.
 * </p>
 * @author Timofey
 * @see ru.simplefuzzy.FuzzyTerm
 * @see ru.simplefuzzy.Fuzzy
 * @see java.util.Vector
 * @since 0.1
 * @version 0.1
 */

public class FuzzyPin {
    public Vector<FuzzyTerm> terms;

    /**
     * Stores input value
     */
    public double value;

    public FuzzyPin(Vector<FuzzyTerm> terms) {
        this.terms = terms;
    }

    public FuzzyPin() {
        this.terms = new Vector<FuzzyTerm>();
    }
}

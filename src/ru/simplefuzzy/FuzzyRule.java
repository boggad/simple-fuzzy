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


public class FuzzyRule {
    public final static int FUZZY_NOT = 1;
    public final static int FUZZY_VERY = 2;
    public final static int FUZZY_QUITE = 3; //более или менее
    public final static int FUZZY_OR = 4;
    public final static int FUZZY_AND = 5;

    private int ruleType;
    public Vector<FuzzyTerm> terms;
    public FuzzyTerm outTerm;
    public double out;

    public FuzzyRule(int ruleType) {
        this.ruleType = ruleType;
        this.terms = new Vector<FuzzyTerm>();
    }

    public void activate() {
        double r = 0;
        switch (ruleType) {
            case FUZZY_NOT: {
                r = 1.0 - terms.firstElement().value;
                break;
            }
            case FUZZY_VERY: {
                r = Math.pow(terms.firstElement().value, 2.0);
                break;
            }
            case FUZZY_QUITE: {
                r = Math.sqrt(terms.firstElement().value);
                break;
            }
            case FUZZY_OR: {
                terms.sort((FuzzyTerm a1, FuzzyTerm a2) -> {
                    if (a1.value == a2.value) return 0;
                    if (a1.value > a2.value) return -1;
                    return 1;
                });
                r = terms.firstElement().value;
                break;
            }
            case FUZZY_AND: {
                terms.sort((FuzzyTerm a1, FuzzyTerm a2) -> {
                    if (a1.value == a2.value) return 0;
                    if (a1.value > a2.value) return 1;
                    return -1;
                });
                r = terms.firstElement().value;
                break;
            }
            default: r = 0;
        }
        out = r;
    }
}

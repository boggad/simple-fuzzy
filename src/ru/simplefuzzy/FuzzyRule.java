package ru.simplefuzzy;

import java.util.HashMap;

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
    public final static int FUZZY_NORMAL = 0;
    public final static int FUZZY_NOT = 1;
    public final static int FUZZY_VERY = 2;
    public final static int FUZZY_QUITE = 3; //более или менее
    public final static int FUZZY_OR = 4;
    public final static int FUZZY_AND = 5;

    private int ruleType;
    private HashMap<FuzzyTerm, Integer> addTerm;
    public FuzzyTerm outTerm;
    public double out;
    private double maxValue, minValue;

    public void addTerm(FuzzyTerm term, int logicType) {
        if (logicType < 0 || logicType > 3) logicType = 0;
        addTerm.put(term, logicType);
    }
    public void addTerm(FuzzyTerm term) {
        addTerm.put(term, FUZZY_NORMAL);
    }

    public FuzzyRule(int ruleType) {
        this.ruleType = ruleType;
        this.addTerm = new HashMap<FuzzyTerm, Integer>();
    }

    public void activate() {
        double r = 0;
        addTerm.forEach((FuzzyTerm key, Integer value) -> {
            switch (value) {
                case FUZZY_NORMAL: break;
                case FUZZY_NOT: key.value = 1.0 - key.value; break;
                case FUZZY_VERY: key.value = Math.pow(key.value, 2.0); break;
                case FUZZY_QUITE: key.value = Math.sqrt(key.value);break;
                default: break;
            }
        });
        maxValue = addTerm.keySet().iterator().next().value;
        minValue = maxValue;
        addTerm.forEach((FuzzyTerm key, Integer value) -> {
            if (key.value > maxValue) maxValue = key.value;
            if (key.value < minValue) minValue = key.value;
        });

        switch (ruleType) {
            case FUZZY_OR: {
                r = maxValue;
                break;
            }
            case FUZZY_AND: {
                r = minValue;
                break;
            }
            default:
                r = 0;
        }
        out = r;
    }
}

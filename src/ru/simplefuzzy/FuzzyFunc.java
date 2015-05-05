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
 * Describes membership function.
 *
 * <p>
 *     The function can be one of 3 kinds: trapezium, bell function or sigmoid. Type of created function depends on
 *     the number of constructor's arguments and is stored in <code>type</code> variable.
 * </p>
 *
 * @author Timofey
 * @see ru.simplefuzzy.Fuzzy
 * @see ru.simplefuzzy.FuzzyTerm
 * @since 0.1
 * @version 0.1
 */

public class FuzzyFunc {
    private double entry, startMax, endMax, exit;
    private double a, b, c; //сигмоидальная/гауссова

    /**
     * Defines membership function's type
     */
    private int type = 0; // 1 = обычная, 2 - сигмоидальная, 3 - гаусс

    /**
     * Constructs a new trapezium membership function.
     *
     * @param entry defines where left side crosses x-axis
     * @param startMax defines where left sides reaches its maximum
     * @param endMax defines where function's maximum ends; can be equal to <code>startMax</code>
     * @param exit defines where right side crosses x-axis
     * @since 0.1
     */
    public FuzzyFunc(double entry, double startMax, double endMax, double exit) {
        this.entry = entry;
        this.startMax = startMax;
        this.endMax = endMax;
        this.exit = exit;
        type = 1;
    }

    /**
     * Constructs a new sigmoid function.
     *
     * @param a defines 'wideness' of the function
     * @param b defines where is a center of the function
     * @since 0.1
     */
    public FuzzyFunc(double a, double b) {
        this.a = a;
        this.b = b;
        type = 2;
    }

    /**
     * Constructs a new bell-curved function.
     *
     * @param a defines 'wideness' of the function
     * @param b defines sharpness of the edges
     * @param c defines where is a center of a function
     * @since 0.1
     */
    public FuzzyFunc(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        type = 3;
    }

    /**
     * Calculates a point that will be used to determine a mass-center
     * @return a point that will be used to determine a mass-center
     * @since 0.1
     */
    public double getMedian() {
        switch (type) {
            case 1:
                return startMax + (endMax - startMax) / 2.0;
            case 2:
                return b;
            case 3:
                return c;
            default:
                return 0;
        }
    }

    /**
     * Calculates the function's value by a given argument.
     *
     * @param input given argument
     * @return calculated value
     * @since 0.1
     */
    public double func(double input) {
        switch (type) {
            case 1: {
                if (input <= entry || input >= exit) return 0.0;
                if (input >= startMax && input <= endMax) return 1.0;
                if (input > entry && input < startMax) {
                    return (input - entry) / (startMax - entry);
                } else {
                    return (endMax - input) / (exit - endMax) + 1.0;
                }
            }
            case 2: {
                return 1.0 / (1 + Math.exp(a * (b - input)));
            }
            case 3: {
                return 1.0 / (1 + Math.pow(Math.abs((input - c) / a), 2 * b));
            }
            default:
                return 0;
        }
    }
}

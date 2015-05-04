package ru.simplefuzzy;

/**
 * Copyright (c) 2015 Timofey Klyubin, The MIT License
 */
public class FuzzyFunc {
    private double entry, startMax, endMax, exit;
    private double a, b, c; //сигмоидальная/гауссова
    private int type = 0; // 1 = обычная, 2 - сигмоидальная, 3 - гаусс


    public FuzzyFunc(double entry, double startMax, double endMax, double exit) {
        this.entry = entry;
        this.startMax = startMax;
        this.endMax = endMax;
        this.exit = exit;
        type = 1;
    }

    public FuzzyFunc(double a, double b) {
        this.a = a;
        this.b = b;
        type = 2;
    }

    public FuzzyFunc(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        type = 3;
    }

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

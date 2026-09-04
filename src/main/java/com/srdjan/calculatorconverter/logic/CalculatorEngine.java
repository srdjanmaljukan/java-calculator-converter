package com.srdjan.calculatorconverter.logic;

public class CalculatorEngine {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public double squareRoot(double a) {
        if (a < 0) {
            throw new ArithmeticException("Cannot take square root of a negative number");
        }
        return Math.sqrt(a);
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double percentage(double value, double percent) {
        return value * (percent / 100.0);
    }
}
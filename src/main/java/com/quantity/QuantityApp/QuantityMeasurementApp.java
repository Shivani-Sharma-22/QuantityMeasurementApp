package com.quantity.QuantityApp;

public class QuantityMeasurementApp {

    public static boolean compareFeet(double a, double b) {
        Feet f1 = new Feet(a);
        Feet f2 = new Feet(b);
        return f1.equals(f2);
    }

    public static boolean compareInches(double a, double b) {
        Inches i1 = new Inches(a);
        Inches i2 = new Inches(b);
        return i1.equals(i2);
    }

    public static boolean feetToInchesEqual(double feet, double inches) {
        Feet f = new Feet(feet);
        Inches i = new Inches(inches);

        return Double.compare(f.toInches(), inches) == 0;
    }

    public static void main(String[] args) {

        System.out.println("1.0 inch vs 1.0 inch → "
                + compareInches(1.0, 1.0));

        System.out.println("1.0 ft vs 1.0 ft → "
                + compareFeet(1.0, 1.0));

        System.out.println("1.0 ft vs 12 inches → "
                + feetToInchesEqual(1.0, 12.0));
    }
}

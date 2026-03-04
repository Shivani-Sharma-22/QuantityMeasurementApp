package com.quantity.QuantityApp;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double toFeet(double value) {
        return value * conversionFactor;
    }
}
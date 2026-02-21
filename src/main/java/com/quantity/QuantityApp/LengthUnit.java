package com.quantity.QuantityApp;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0);   // 1 inch = 1/12 feet

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double toBaseUnit(double value) {
        return value * conversionFactor;
    }
}



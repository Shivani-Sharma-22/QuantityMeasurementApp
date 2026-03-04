package com.quantity.QuantityApp;

public enum LengthUnit {

    FEET(1.0),// Base unit
    INCH(1.0 / 12.0),   // 1 inch = 1/12 feet
	YARDS(3.0), //i yrad = 3 feet
	CENTIMETERS(1.0 / 30.48); //1 cm =  0.393701 inches

	
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double getConversionFactor() {
    	return conversionFactor;
    }
 // UC8 responsibility: convert value in this unit to base (FEET)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // UC8 responsibility: convert value from base (FEET) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}



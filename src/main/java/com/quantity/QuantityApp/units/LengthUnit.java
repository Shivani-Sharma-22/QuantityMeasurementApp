package com.quantity.QuantityApp.units;

public enum LengthUnit implements IMeasurable{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    METERS(39.3701),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    private LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public String getUnitName(){
        return this.name();
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value){
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double value){
        return value / conversionFactor;
    }
}

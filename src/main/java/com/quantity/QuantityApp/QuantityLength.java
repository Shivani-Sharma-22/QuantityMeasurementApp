package com.quantity.QuantityApp;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

   
    
    /**
     * Converts this QuantityLength to a target unit.
     * Returns a new immutable QuantityLength instance.
     */
    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double convertedValue = convert(this.value, this.unit, target);
        return new QuantityLength(convertedValue, target);
    }
    
    /**
     * Static conversion API.
     * Converts a value from source unit to target unit.
     */
    public static double convert(double value,
            LengthUnit source,
            LengthUnit target) {
    	if(!Double.isFinite(value)) {
    		throw new IllegalArgumentException("Invalid numeric value");
    	}
    	if(target == null || source == null) {
    		throw new IllegalArgumentException("Unit cannot be null");
    	}
          return value * (source.getConversionFactor() /
                  target.getConversionFactor());
    }
    

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;              // Reflexive
        if (obj == null) return false;             // Null safety
        if (getClass() != obj.getClass()) return false; // Type safety

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.convertToBase()-
                other.convertToBase()) < EPSILON;
    }
    /**
     * Converts current value to base unit (feet).
     * Private helper for comparison.
     */
    private double convertToBase() {
        return unit.toFeet(value);
    }
    @Override
    public String toString() {
        return "(" + value + ", " + unit + ")";
    }

    /**
     * If two objects are equal → they produce same hashCode.
     */
    @Override
    public int hashCode() {
    	long normalized = Math.round(convertToBase() / EPSILON);
        return Objects.hash(normalized);
    }

}



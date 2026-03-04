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
    /** 
     * Quantity Length API to add two lengths
     */
    private static double fromBase(double baseValue,
            LengthUnit target) {
       return baseValue / target.getConversionFactor();
}
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Cannot add null quantity");
        }

        double sumBase = this.convertToBase()
                          + other.convertToBase();

        double result = fromBase(sumBase, this.unit);

        return new QuantityLength(result, this.unit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityLength other)) return false;

        double baseThis = this.convertToBase();
        double baseOther = other.convertToBase();

        return Math.abs(baseThis - baseOther) < EPSILON;
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
    	return value + " " + unit;
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



package com.quantity.QuantityApp;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (value < 0)
            throw new IllegalArgumentException("Value must be numeric");

        this.value = value;
        this.unit = unit;
    }

    public double toFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || !(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        double diff = Math.abs(this.toFeet() - other.toFeet());
        return diff < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toFeet());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}



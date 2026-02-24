package com.quantity.QuantityApp;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

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

    private double convertToBase() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;              // Reflexive
        if (obj == null) return false;             // Null safety
        if (getClass() != obj.getClass()) return false; // Type safety

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(this.convertToBase(),
                other.convertToBase()) == 0;
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBase());
    }
}



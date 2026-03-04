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
    //  UC5 Conversion (delegated to LengthUnit)
    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new QuantityLength(converted, target);
    }


    /**
     * UC6: Add two QuantityLength objects (result in first operand's unit)
     */
    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit); // delegate to UC7 version
    }

    /**
     * UC7: Add two QuantityLength objects with explicit target unit
     */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add null quantity");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase = unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double finalValue = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(finalValue, targetUnit);
    }

   

  // Equality using base unit
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength other)) return false;

        double baseThis = unit.convertToBaseUnit(value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return Math.abs(baseThis - baseOther) < EPSILON;
    }

    @Override
    public int hashCode() {
    	long normalized = Math.round(unit.convertToBaseUnit(value) / EPSILON);
        return Objects.hash(normalized);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
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
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        return value * (source.getConversionFactor() / target.getConversionFactor());
    }

    /**
     * Converts base unit value to target unit.
     */
    private static double fromBase(double baseValue, LengthUnit target) {
        return baseValue / target.getConversionFactor();
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

        // Convert both quantities to base unit (feet)
        double sumBase = this.convertToBase() + other.convertToBase();

        // Convert sum to target unit
        double result = fromBase(sumBase, targetUnit);

        return new QuantityLength(result, targetUnit);
    }

    /**
     * Converts current value to base unit (feet) for internal use
     */
    private double convertToBase() {
        return unit.toFeet(value);
    }

    /**
     * Equality check based on base unit (feet) with epsilon tolerance
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength other)) return false;

        return Math.abs(this.convertToBase() - other.convertToBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(convertToBase() / EPSILON);
        return Objects.hash(normalized);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
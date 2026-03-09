package com.quantity.QuantityApp;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 1e-6;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert to base unit (internal)
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // General conversion
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(converted, targetUnit);
    }

    // Helper for validation
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {
        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
    }

    // Helper to perform arithmetic
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = this.toBaseUnit();
        double otherBase = other.toBaseUnit();
        return operation.compute(thisBase, otherBase);
    }

    // --- Arithmetic Methods ---

    public Quantity<U> add(Quantity<U> other) {
        this.unit.validateOperationSupport("add");
        other.unit.validateOperationSupport("add");
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double finalValue = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(finalValue, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        this.unit.validateOperationSupport("subtract");
        other.unit.validateOperationSupport("subtract");
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double finalValue = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(finalValue, targetUnit);
    }

    public double divide(Quantity<U> other) {
        this.unit.validateOperationSupport("divide");
        other.unit.validateOperationSupport("divide");
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // --- Equality & Hash ---
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        // Compare enum types (TemperatureUnit, LengthUnit, etc.)
        if (!this.unit.getClass().getSuperclass().equals(other.unit.getClass().getSuperclass())
            && !this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        // Compare values in base units (Celsius for temperature)
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(toBaseUnit() / EPSILON);
        return Objects.hash(normalized, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
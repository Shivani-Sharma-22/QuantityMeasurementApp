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

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double finalValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(finalValue, targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other){
    	return subtract(other,this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
    	if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        
        double subBase = this.toBaseUnit() - other.toBaseUnit();
        double finalAns = targetUnit.convertFromBaseUnit(subBase);
        
        return new Quantity<>(finalAns,targetUnit);
		
	}
   
    public Quantity<U> divide(Quantity<U> other) {
        return divide(other, this.unit); // default target is this.unit
    }

    public Quantity<U> divide(Quantity<U> other, U targetUnit) {
        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        // CROSS-CATEGORY CHECK
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot operate on different measurement categories");

        double divisor = other.toBaseUnit();

        if (Math.abs(divisor) < EPSILON)
            throw new ArithmeticException("Division by zero");

        double resultBase = this.toBaseUnit() / divisor;
        double finalValue = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(finalValue, targetUnit);
    }

	@Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

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
package com.quantity.QuantityApp;

public class Inches {
    private final double value;

    public Inches(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Inch value must be numeric and non-negative");
        }
        this.value = value;
    }

    public double toFeet() {
        return value / 12;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Inches)) return false;

        Inches other = (Inches) obj;
        return Double.compare(this.value, other.value) == 0;
    }
}


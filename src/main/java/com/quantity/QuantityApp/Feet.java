package com.quantity.QuantityApp;

public class Feet {
    private final double value;

    public Feet(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Feet value must be numeric and non-negative");
        }
        this.value = value;
    }

    public double toInches() {
        return value * 12;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Feet)) return false;

        Feet other = (Feet) obj;
        return Double.compare(this.value, other.value) == 0;
    }
}


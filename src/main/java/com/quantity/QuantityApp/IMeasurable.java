package com.quantity.QuantityApp;

public interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();

    // Default: all units support arithmetic
    default boolean supportsArithmetic() {
        return true;
    }

    default boolean supportsAddition() {
        return supportsArithmetic();
    }

    default boolean supportsSubtraction() {
        return supportsArithmetic();
    }

    default boolean supportsDivision() {
        return supportsArithmetic();
    }

    // Default validation (only temperature overrides)
    default void validateOperationSupport(String operation) {
        // do nothing
    }
}
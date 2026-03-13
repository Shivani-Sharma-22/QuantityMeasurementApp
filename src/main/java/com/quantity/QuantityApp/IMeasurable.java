package com.quantity.QuantityApp;

public interface IMeasurable {
	double getConversionFactor(); 
	double convertToBaseUnit(double value);
	double convertFromBaseUnit(double baseValue);
	String getUnitName();
	
	// UC14 ---
	// by default all units support arithmetic
    default boolean supportsArithmetic() {
        return true;
    }

    // validation hook
    default void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                getUnitName() + " does not support " + operation
            );
        }
    }
    
	// UC15 as helper method
	default String getMeasurementType() {
		return this.getClass().getSimpleName();
	}

	static IMeasurable getUnitFromName(String name, Class<? extends Enum<?>> enumClass) {
		for (Object constant : enumClass.getEnumConstants()) {
			IMeasurable unit = (IMeasurable) constant;
			if (unit.getUnitName().equalsIgnoreCase(name)) {
				return unit;
			}
		}
		throw new IllegalArgumentException("Invalid unit: " + name);
	}
	
}

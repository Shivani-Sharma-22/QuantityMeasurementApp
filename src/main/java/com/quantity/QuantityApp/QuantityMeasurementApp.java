package com.quantity.QuantityApp;

public class QuantityMeasurementApp {

	public static void demonstrateCrossCategoryEquality(Quantity<?> q1, Quantity<?> q2) {
	    System.out.println(q1 + " equals " + q2 + " ? → " + q1.equals(q2));
	}

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q, U target) {
        System.out.println(q + " converted → " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U target) {
        try {
            System.out.println(q1 + " + " + q2 + " → " + q1.add(q2, target));
        } catch (UnsupportedOperationException e) {
            System.out.println("Operation not supported: " + e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U target) {
        try {
            System.out.println(q1 + " - " + q2 + " → " + q1.subtract(q2, target));
        } catch (UnsupportedOperationException e) {
            System.out.println("Operation not supported: " + e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateDivide(
            Quantity<U> q1, Quantity<U> q2) {
        try {
            System.out.println(q1 + " / " + q2 + " → " + q1.divide(q2));
        } catch (UnsupportedOperationException e) {
            System.out.println("Operation not supported: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("=== LENGTH EXAMPLES ===");
        Quantity<LengthUnit> length1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(120, LengthUnit.INCH);

        demonstrateCrossCategoryEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCH);
        demonstrateAddition(length1, length2, LengthUnit.FEET);
        demonstrateSubtraction(length1, length2, LengthUnit.FEET);
        demonstrateDivide(length1, length2);

        System.out.println("\n=== TEMPERATURE EXAMPLES ===");
        Quantity<TemperatureUnit> tempC = new Quantity<>(32.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> tempF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> tempK = new Quantity<>(373.15, TemperatureUnit.KELVIN);

        demonstrateCrossCategoryEquality(tempC, tempF);
        demonstrateCrossCategoryEquality(tempC, tempK);

        demonstrateConversion(tempC, TemperatureUnit.FAHRENHEIT);
        demonstrateConversion(tempF, TemperatureUnit.CELSIUS);
        demonstrateConversion(tempK, TemperatureUnit.CELSIUS);

        System.out.println("\n=== UNSUPPORTED OPERATIONS ON TEMPERATURE ===");
        demonstrateAddition(tempC, tempC, TemperatureUnit.CELSIUS);
        demonstrateSubtraction(tempC, tempC, TemperatureUnit.CELSIUS);
        demonstrateDivide(tempC, tempC);

        System.out.println("\n=== CROSS-CATEGORY COMPARISON ===");
        demonstrateCrossCategoryEquality(tempC, length1); // Should safely return false
    }
}
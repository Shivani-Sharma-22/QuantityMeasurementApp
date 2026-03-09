package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	private static final double EPSILON = 1e-6;
	
	

   
	
	// === Equality Tests ===

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    

  
    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> q = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assertTrue(q.equals(q));
    }

    // === Conversion Tests ===

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        assertEquals(32.0, new Quantity<>(0.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
        assertEquals(122.0, new Quantity<>(50.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
        assertEquals(-4.0, new Quantity<>(-20.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        assertEquals(0.0, new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
        assertEquals(50.0, new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
        assertEquals(-20.0, new Quantity<>(-4.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {
        double[] values = {0.0, 50.0, -20.0, 100.0, -40.0};
        for (double val : values) {
            Quantity<TemperatureUnit> originalC = new Quantity<>(val, TemperatureUnit.CELSIUS);
            Quantity<TemperatureUnit> roundTripC = originalC.convertTo(TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS);
            assertEquals(val, roundTripC.getValue(), EPSILON);

            Quantity<TemperatureUnit> originalF = new Quantity<>(val, TemperatureUnit.FAHRENHEIT);
            Quantity<TemperatureUnit> roundTripF = originalF.convertTo(TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT);
            assertEquals(val, roundTripF.getValue(), EPSILON);
        }
    }

    // === Unsupported Operations Tests ===

    @Test
    void testTemperatureAdd_ThrowsException() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(5.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
    }

    @Test
    void testTemperatureSubtract_ThrowsException() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(5.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> q1.subtract(q2));
    }

    @Test
    void testTemperatureDivide_ThrowsException() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(5.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> q1.divide(q2));
    }
 // === Conversion to Same Unit ===
    @Test
    void SameUnit() {
        Quantity<TemperatureUnit> qC = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultC = qC.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(25.0, resultC.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(77.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultF = qF.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(77.0, resultF.getValue(), EPSILON);
    }

    // === Conversion of Zero ===
    @Test
    void ZeroValue() {
        Quantity<TemperatureUnit> qC = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(32.0, resultF.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultC = qF.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, resultC.getValue(), EPSILON);
    }

    // === Conversion of Negative Values ===
    @Test
    void testNegativeValues() {
        Quantity<TemperatureUnit> qC = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(-40.0, resultF.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultC = qF.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(-40.0, resultC.getValue(), EPSILON);
    }

    // === Conversion of Large Values ===
    @Test
    void LargeValues() {
        Quantity<TemperatureUnit> qC = new Quantity<>(1000.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(1832.0, resultF.getValue(), EPSILON);
    }

    // === Unsupported Operations Tests ===
    @Test
    void testTemperatureAdd() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
        assertTrue(ex.getMessage().contains("add"));
    }

    @Test
    void testTemperatureSubtract() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.subtract(q2));
        assertTrue(ex.getMessage().contains("subtract"));
    }

    @Test
    void testTemperatureDivide() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.divide(q2));
        assertTrue(ex.getMessage().contains("divide"));
    }

    @Test
    void testTemperature_ErrorMessage() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
        assertEquals("Temperature does not support add operation", ex.getMessage());
    }

    // === Cross-Category Equality Prevention ===
    @Test
    void testTemperatureVsLengthIncompatibility() {
        Quantity<TemperatureUnit> qTemp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> qLength = new Quantity<>(100.0, LengthUnit.FEET);
        assertFalse(qTemp.equals(qLength));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {
        Quantity<TemperatureUnit> qTemp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> qWeight = new Quantity<>(50.0, WeightUnit.KILOGRAM);
        assertFalse(qTemp.equals(qWeight));
    }
 // === Conversion to Same Unit ===
    @Test
    void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> qC = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultC = qC.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(25.0, resultC.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(77.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultF = qF.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(77.0, resultF.getValue(), EPSILON);
    }

    // === Conversion of Zero ===
    @Test
    void testTemperatureConversion_ZeroValue() {
        Quantity<TemperatureUnit> qC = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(32.0, resultF.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultC = qF.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, resultC.getValue(), EPSILON);
    }

    // === Conversion of Negative Values ===
    @Test
    void testTemperatureConversion_NegativeValues() {
        Quantity<TemperatureUnit> qC = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(-40.0, resultF.getValue(), EPSILON);

        Quantity<TemperatureUnit> qF = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> resultC = qF.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(-40.0, resultC.getValue(), EPSILON);
    }
 // === Conversion of Large Values ===
    @Test
    void testTemperatureConversion_LargeValues() {
        Quantity<TemperatureUnit> qC = new Quantity<>(1000.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> resultF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(1832.0, resultF.getValue(), EPSILON);
    }

    // === Unsupported Operations Tests ===
    @Test
    void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
        assertTrue(ex.getMessage().contains("add"));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.subtract(q2));
        assertTrue(ex.getMessage().contains("subtract"));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.divide(q2));
        assertTrue(ex.getMessage().contains("divide"));
    }

    @Test
    void testTemperatureUnsupportedOperation_ErrorMessage() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
        assertEquals("Temperature does not support add operation", ex.getMessage());
    }
 // === Cross-category prevention ===
    @Test
    void testTemperatureVsVolumeIncompatibility() {
        Quantity<TemperatureUnit> qTemp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<VolumeUnit> qVol = new Quantity<>(25.0, VolumeUnit.LITRE);
        assertFalse(qTemp.equals(qVol));
    }

    // === Operation support for TemperatureUnit ===
    @Test
    void testOperationSupportMethods_TemperatureUnitAddition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsAddition());
    }

    @Test
    void testOperationSupportMethods_TemperatureUnitDivision() {
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsDivision());
    }

    // === Operation support for LengthUnit and WeightUnit ===
    @Test
    void testOperationSupportMethods_LengthUnitAddition() {
        assertTrue(LengthUnit.FEET.supportsAddition());
    }

    @Test
    void testOperationSupportMethods_WeightUnitDivision() {
        assertTrue(WeightUnit.KILOGRAM.supportsDivision());
    }

    // === IMeasurable backward compatibility ===
    @Test
    void testIMeasurableInterface_Evolution_BackwardCompatible() {
        
        WeightUnit w = WeightUnit.KILOGRAM;
        VolumeUnit v = VolumeUnit.LITRE;

        
        assertEquals("KILOGRAM", w.getUnitName());
        assertEquals("LITRE", v.getUnitName());
    }
 // === Non-linear temperature conversions ===
    @Test
    void testTemperatureUnit_NonLinearConversion() {
        Quantity<TemperatureUnit> qC = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> qF = qC.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, qF.getValue(), EPSILON);

        Quantity<TemperatureUnit> qK = qC.convertTo(TemperatureUnit.KELVIN);
        assertEquals(373.15, qK.getValue(), EPSILON);
    }

    // === Access to TemperatureUnit constants ===
    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
        assertNotNull(TemperatureUnit.KELVIN);
    }

    // === getUnitName() ===
    @Test
    void testTemperatureUnit_NameMethod() {
        assertEquals("Celsius", TemperatureUnit.CELSIUS.getUnitName());
        assertEquals("Fahrenheit", TemperatureUnit.FAHRENHEIT.getUnitName());
        assertEquals("Kelvin", TemperatureUnit.KELVIN.getUnitName());
    }

    // === getConversionFactor() placeholder ===
    @Test
    void testTemperatureUnit_ConversionFactor() {
        assertEquals(1.0, TemperatureUnit.CELSIUS.getConversionFactor());
        assertEquals(1.0, TemperatureUnit.FAHRENHEIT.getConversionFactor());
        assertEquals(1.0, TemperatureUnit.KELVIN.getConversionFactor());
    }

    // === Symmetric property of temperature equality ===
    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> qC = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> qF = new Quantity<>(77.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(qC.equals(qF));
        assertTrue(qF.equals(qC));
    }
    @Test
    void testTemperatureNullUnitValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(100.0, null));
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {
        Quantity<TemperatureUnit> q = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assertFalse(q.equals(null));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testTemperatureBackwardCompatibility_UC1_Through_UC13() {
        // Verify existing LengthUnit, WeightUnit, VolumeUnit operations remain unchanged
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(120.0, LengthUnit.INCH);
        assertEquals(20.0, l1.add(l2, LengthUnit.FEET).getValue(), 1e-6);

        Quantity<WeightUnit> w1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(5.5, w1.add(w2, WeightUnit.KILOGRAM).getValue(), 1e-6);
    }


    @Test
    void testTemperatureConversionEdgeCase_VerySmallDifference() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.000001, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0000018, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(TemperatureUnit.CELSIUS instanceof IMeasurable);
        assertTrue(TemperatureUnit.FAHRENHEIT instanceof IMeasurable);
    }

    @Test
    void testTemperatureDefaultMethodInheritance() {
        // Non-temperature units inherit default true support for arithmetic
        assertTrue((LengthUnit.FEET).supportsArithmetic());
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
        assertTrue(VolumeUnit.LITRE.supportsArithmetic());
    }

    @Test
    void testTemperatureCrossUnitAdditionAttempt() {
        Quantity<TemperatureUnit> qC = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> qF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertThrows(UnsupportedOperationException.class, () -> qC.add(qF));
    }
    
	 
}



package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

    // Constructor Validation

    @Test
    void testLengthUnitEnum_FeetConstant() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);
        assertEquals(5.0, q.getValue());
        assertEquals(LengthUnit.FEET, q.getUnit());
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(5.0, null));
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // Conversion Tests (UC5)

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = q.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        QuantityLength q = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = q.convertTo(LengthUnit.FEET);
        assertEquals(1.0, result.getValue(), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        QuantityLength q = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = q.convertTo(LengthUnit.YARDS);
        assertEquals(1.0, result.getValue(), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        QuantityLength q = new QuantityLength(30.48, LengthUnit.CENTIMETERS);
        QuantityLength result = q.convertTo(LengthUnit.FEET);
        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void roundTripConversion() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength converted = q.convertTo(LengthUnit.INCH)
                                     .convertTo(LengthUnit.FEET);
        assertEquals(q.getValue(), converted.getValue(), 0.0001);
    }

    @Test
    void convertToSameUnitShouldRemainSame() {
        QuantityLength q = new QuantityLength(10.0, LengthUnit.YARDS);
        QuantityLength result = q.convertTo(LengthUnit.YARDS);
        assertEquals(10.0, result.getValue());
    }

    // Addition UC6

    @Test
    void addFeetAndInches_DefaultUnit() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void addInchesAndFeet_DefaultUnit() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(24.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // Addition UC7 (Target Unit)

    @Test
    void addWithTargetUnitFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void addWithTargetUnitInches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2, LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.0001);
    }

    @Test
    void addWithTargetUnitYards() {
        QuantityLength q1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2, LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void shouldThrowIfOtherNullInAdd() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.add(null));
    }

    @Test
    void shouldThrowIfTargetUnitNull() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(q2, null));
    }

    // Equality Tests

    @Test
    void shouldBeEqualDifferentUnits() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void shouldNotBeEqualDifferentValues() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void equalsShouldBeReflexive() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void equalsShouldHandleNull() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);
        assertFalse(q.equals(null));
    }

    @Test
    void equalsShouldHandleDifferentObject() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);
        assertFalse(q.equals("Test"));
    }

    // HashCode Contract

    @Test
    void equalObjectsMustHaveSameHashCode() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    // Edge Cases

    @Test
    void zeroValueTest() {
        QuantityLength q = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength result = q.convertTo(LengthUnit.INCH);

        assertEquals(0.0, result.getValue());
    }

    @Test
    void negativeValueTest() {
        QuantityLength q1 = new QuantityLength(-1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(0.0, result.getValue(), 0.0001);
    }

    @Test
    void precisionToleranceTest() {
        QuantityLength q1 = new QuantityLength(1.0000001, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0000002, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void additionCommutativeTest() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(q1.add(q2).equals(q2.add(q1)));
    }
}



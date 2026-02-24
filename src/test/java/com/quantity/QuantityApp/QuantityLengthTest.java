package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCH);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_InvalidNumeric() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
    @Test
    void testConvertTo_FeetToInch() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength converted = q.convertTo(LengthUnit.INCH);

        assertEquals(12.0, converted.getValue());
        assertEquals(LengthUnit.INCH, converted.getUnit());
    }
    @Test
    void testConvertTo_InchToFeet() {
        QuantityLength q = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength converted = q.convertTo(LengthUnit.FEET);

        assertEquals(1.0, converted.getValue());
        assertEquals(LengthUnit.FEET, converted.getUnit());
    }
    @Test
    void testConvertTo_YardToFeet() {
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength converted = q.convertTo(LengthUnit.FEET);

        assertEquals(6.0, converted.getValue());
        assertEquals(LengthUnit.FEET, converted.getUnit());
    }
    @Test
    void testConvertTo_NegativeValue() {
        QuantityLength q = new QuantityLength(-2.0, LengthUnit.YARDS);
        QuantityLength converted = q.convertTo(LengthUnit.FEET);

        assertEquals(-6.0, converted.getValue());
    }
    @Test
    void testConvertTo_ZeroValue() {
        QuantityLength q = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength converted = q.convertTo(LengthUnit.INCH);

        assertEquals(0.0, converted.getValue());
    }
    @Test
    void testConvertTo_NullTarget() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.convertTo(null));
    }
}



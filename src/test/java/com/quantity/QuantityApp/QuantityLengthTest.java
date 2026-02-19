package com.quantity.QuantityApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityLengthTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(new QuantityLength(1, LengthUnit.FEET)
                .equals(new QuantityLength(1, LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(new QuantityLength(12, LengthUnit.INCH)
                .equals(new QuantityLength(1, LengthUnit.FEET)));
    }

    @Test
    void testEquality_DifferentValues() {
        assertFalse(new QuantityLength(1, LengthUnit.FEET)
                .equals(new QuantityLength(2, LengthUnit.FEET)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityLength(1, LengthUnit.FEET)
                .equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q =
                new QuantityLength(1, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1, null));
    }
}



package com.quantity.QuantityApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    void testEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    @Test
    void testEquality_NullComparison() {
        Feet f = new Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Feet f = new Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testFeetToInchesEquality() {
        assertTrue(QuantityMeasurementApp.feetToInchesEqual(1.0, 12.0));
    }
}


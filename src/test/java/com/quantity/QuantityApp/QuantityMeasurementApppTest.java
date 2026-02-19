package com.quantity.QuantityApp;

package com.quantity.QuantityApp;

import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuantityMeasurementApppTest {
     
	@Test
	void testEquality_sameValue() {
		QuantityMeasuringApp.Feet feet1=new QuantityMeasuringApp.Feet(1.0);
		QuantityMeasuringApp.Feet feet2 = new QuantityMeasuringApp.Feet(1.0);

	        assertTrue(feet1.equals(feet2), "1.0 ft should be equal to 1.0 ft");
	}
	
	@Test
    void testEquality_DifferentValue() {
		QuantityMeasuringApp.Feet feet1 = new QuantityMeasuringApp.Feet(1.0);
		QuantityMeasuringApp.Feet feet2 = new QuantityMeasuringApp.Feet(2.0);

        assertFalse(feet1.equals(feet2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
    	QuantityMeasuringApp.Feet feet1 = new QuantityMeasuringApp.Feet(1.0);

        assertFalse(feet1.equals(null), "Value should not be equal to null");
    }
    
    @Test
    void testEquality_SameReference() {
    	QuantityMeasuringApp.Feet feet1 = new QuantityMeasuringApp.Feet(1.0);

        assertTrue(feet1.equals(feet1), "Object should be equal to itself");
    }

    @Test
    void testEquality_NonNumericInput() {
    	QuantityMeasuringApp.Feet feet1 = new QuantityMeasuringApp.Feet(1.0);

        assertFalse(feet1.equals("NonNumeric"), "Feet object should not equal a non-numeric input");
    }
}

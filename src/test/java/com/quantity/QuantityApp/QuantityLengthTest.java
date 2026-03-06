package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	private static final double EPSILON = 1e-6;
	
	//subtract test cases
	@Test
	void SameUnit_FeetMinusFeet() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
    	Quantity<LengthUnit> result = a.subtract(b);
    	// Option 1
        assertEquals(5.0, result.getValue(), 1e-6);

        // Option 2 (preferred, checks unit as well)
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
	}
	@Test
	void SameUnit_LitreMinusLitre() {
		Quantity<VolumeUnit> a = new Quantity<>(10.0, VolumeUnit.LITRE);
    	Quantity<VolumeUnit> b = new Quantity<>(3.0, VolumeUnit.LITRE);
    	Quantity<VolumeUnit> result = a.subtract(b);
    	assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
	}

	@Test
	void CrossUnit_FeetMinusInches() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(9.5, LengthUnit.FEET),result);
	}
	@Test
	void CrossUnit_InchesMinusFeet() {
		Quantity<LengthUnit> a = new Quantity<>(120.0, LengthUnit.INCH);
    	Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(60.0, LengthUnit.INCH),result);
	}
	
	@Test
	void ExplicitTargetUnit_Feet() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
    	
    	Quantity<LengthUnit> result = a.subtract(b, LengthUnit.FEET);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
	}
	@Test
	void ExplicitTargetUnit_Inches() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
    	
    	Quantity<LengthUnit> result = a.subtract(b, LengthUnit.INCH);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCH), result);
	}
	@Test
	void ResultingInNegative() {
		Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(-5.0, LengthUnit.FEET),result);
	}
	@Test
	void ResultingInZero() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(120.0, LengthUnit.INCH);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(0.0, LengthUnit.FEET),result);
	}
	@Test
	void WithZeroOperand() {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.INCH);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(10.0, LengthUnit.FEET),result);
	}
	@Test
	void WithNegativeValues() {
		Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(-2.0, LengthUnit.FEET);
    	Quantity<LengthUnit> result = a.subtract(b);
    	
    	assertEquals(new Quantity<>(7.0, LengthUnit.FEET),result);
	}
	@Test
	void NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), a.subtract(b));
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), b.subtract(a));
    }
	 @Test
	    void WithLargeValues() {
	        Quantity<WeightUnit> a = new Quantity<>(1e6, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> b = new Quantity<>(5e5, WeightUnit.KILOGRAM);

	        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), a.subtract(b));
	    }
	 @Test
	    void testSubtraction_NullOperand() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        assertThrows(IllegalArgumentException.class, () -> a.subtract(null));
	    }

	    @Test
	    void testSubtraction_NullTargetUnit() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
	        assertThrows(IllegalArgumentException.class, () -> a.subtract(b, null));
	    }

	    

	    @Test
	    void testSubtraction_AllMeasurementCategories() {
	        Quantity<LengthUnit> len = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
	        Quantity<VolumeUnit> vol = new Quantity<>(2.0, VolumeUnit.LITRE);

	        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), len.subtract(new Quantity<>(5.0, LengthUnit.FEET)));
	        assertEquals(new Quantity<>(3.0, WeightUnit.KILOGRAM), weight.subtract(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
	        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), vol.subtract(new Quantity<>(1.0, VolumeUnit.LITRE)));
	    }

	    @Test
	    void testSubtraction_ChainedOperations() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> result = a.subtract(new Quantity<>(2.0, LengthUnit.FEET))
	                                     .subtract(new Quantity<>(1.0, LengthUnit.FEET));
	        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
	    }

	    @Test
	    void testSubtraction_AdditionInverse() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	        Quantity<LengthUnit> result = a.add(b).subtract(b);
	        assertEquals(a, result);
	    }

	    @Test
	    void testSubtraction_Immutability() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	        a.subtract(b);
	        // a should remain unchanged
	        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
	    }

	    @Test
	    void testSubtraction_PrecisionAndRounding() {
	        Quantity<LengthUnit> a = new Quantity<>(1.000001, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(0.000001, LengthUnit.FEET);

	        Quantity<LengthUnit> result = a.subtract(b);
	        assertEquals(1.0, result.getValue(), EPSILON);
	    }

	    @Test
        void testDivision_ByZero() {
            Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);

            assertThrows(ArithmeticException.class, () -> a.divide(b));
        }
	    @Test
	    void testDivision_NullOperand() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        assertThrows(IllegalArgumentException.class, () -> a.divide(null));
	    }
	    
	    @Test
	    void testDivision_AllMeasurementCategories() {
	        Quantity<LengthUnit> len = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
	        Quantity<VolumeUnit> vol = new Quantity<>(2.0, VolumeUnit.LITRE);

	        assertEquals(2.0, len.divide(new Quantity<>(5.0, LengthUnit.FEET)).getValue(), EPSILON);
	        assertEquals(2.0, weight.divide(new Quantity<>(2.5, WeightUnit.KILOGRAM)).getValue(), EPSILON);
	        assertEquals(2.0, vol.divide(new Quantity<>(1.0, VolumeUnit.LITRE)).getValue(), EPSILON);
	    }

	    @Test
	    void testDivision_Associativity() {
	        Quantity<LengthUnit> a = new Quantity<>(12.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
	        Quantity<LengthUnit> c = new Quantity<>(2.0, LengthUnit.FEET);

	        assertNotEquals(a.divide(b).divide(c).getValue(), a.divide(b.divide(c)).getValue());
	    }

	    @Test
	    void testSubtractionAndDivision_Integration() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
	        Quantity<LengthUnit> c = new Quantity<>(2.0, LengthUnit.FEET);

	        Quantity<LengthUnit> result = a.subtract(b).divide(c);
	        assertEquals(4.0, result.getValue(), EPSILON);
	    }

	    @Test
	    void testDivision_Immutability() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	        a.divide(b);
	        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
	    }
	    @Test
	    void testDivision_CrossCategory() {
	        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<WeightUnit> b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

	        assertThrows(IllegalArgumentException.class, () -> a.divide((Quantity) b));
	    }
}



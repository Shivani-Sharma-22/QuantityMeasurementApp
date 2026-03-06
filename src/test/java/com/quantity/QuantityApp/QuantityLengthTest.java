package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	private static final double EPSILON = 1e-6;

   //======interface implementation test========
 //  Interface Implementation Tests
 @Test
 void LengthUnitImplementation() {
     IMeasurable unit = LengthUnit.FEET;
     assertEquals(1.0, unit.getConversionFactor());
     assertEquals("FEET", unit.getUnitName());
 }
 @Test
 void WeightUnitImplementation() {
     IMeasurable unit = WeightUnit.KILOGRAM;
     assertEquals(1.0,unit.getConversionFactor());
     assertEquals("KILOGRAM",unit.getUnitName());
 }
//️⃣ Length Equality
 @Test
 void testGenericQuantity_LengthOperations_Equality() {
     Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

     assertTrue(q1.equals(q2));
 }

 //  Weight Equality
 @Test
 void testGenericQuantity_WeightOperations_Equality() {
     Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
     Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

     assertTrue(q1.equals(q2));
 }

 //️⃣ Length Conversion
 @Test
 void testGenericQuantity_LengthOperations_Conversion() {
     Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

     Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCH);

     assertEquals(12.0, result.getValue(), EPSILON);
     assertEquals(LengthUnit.INCH, result.getUnit());
 }

 //️⃣ Weight Conversion
 @Test
 void testGenericQuantity_WeightOperations_Conversion() {
     Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

     Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

     assertEquals(1000.0, result.getValue(), EPSILON);
     assertEquals(WeightUnit.GRAM, result.getUnit());
 }

 //  Length Addition
 @Test
 void testGenericQuantity_LengthOperations_Addition() {
     Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);

     Quantity<LengthUnit> result = a.add(b, LengthUnit.FEET);

     assertEquals(2.0, result.getValue(), EPSILON);
     assertEquals(LengthUnit.FEET, result.getUnit());
 }

 //  Weight Addition
 @Test
 void testGenericQuantity_WeightOperations_Addition() {
     Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
     Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);

     Quantity<WeightUnit> result = a.add(b, WeightUnit.KILOGRAM);

     assertEquals(2.0, result.getValue(), EPSILON);
     assertEquals(WeightUnit.KILOGRAM, result.getUnit());
 }

 // Cross Category Prevention
 @Test
 void testCrossCategoryPrevention_LengthVsWeight() {
     Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

     assertFalse(length.equals(weight));
 }

 // Constructor Validation
 @Test
 void testGenericQuantity_ConstructorValidation_NullUnit() {
     assertThrows(IllegalArgumentException.class,
             () -> new Quantity<>(1.0, null));
 }

 @Test
 void testGenericQuantity_ConstructorValidation_InvalidValue() {
     assertThrows(IllegalArgumentException.class,
             () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
 }

 // HashCode consistency
 @Test
 void testHashCode_GenericQuantity_Consistency() {
     Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

     assertEquals(q1.hashCode(), q2.hashCode());
 }

 //  Equals Contract
 @Test
 void testEquals_GenericQuantity_ContractPreservation() {

     Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
     Quantity<LengthUnit> c = new Quantity<>(1.0 / 3.0, LengthUnit.YARDS);

     assertTrue(a.equals(b));
     assertTrue(b.equals(c));
     assertTrue(a.equals(c));
 }

 //  Immutability
 @Test
 void testImmutability_GenericQuantity() {

     Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
     Quantity<LengthUnit> b = a.convertTo(LengthUnit.INCH);

     assertNotSame(a, b);
 }
   
}



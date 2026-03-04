package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

   // 1Explicit Target Unit: FEET
   @Test
   void testAddition_ExplicitTargetUnit_Feet() {
       QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
       QuantityLength b = new QuantityLength(12.0,LengthUnit.INCH);
       
       QuantityLength result = a.add(b,LengthUnit.FEET);
       assertEquals(2.0,result.getValue(),1e-6);
       assertEquals(LengthUnit.FEET,result.getUnit());
   }
// Explicit Target Unit: INCHES
   @Test
   void testAddition_ExplicitTargetUnit_Inches() {
       QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
       QuantityLength b = new QuantityLength(12.0,LengthUnit.INCH);
       
       QuantityLength result = a.add(b,LengthUnit.INCH);
       assertEquals(24.0,result.getValue(),1e-6);
       assertEquals(LengthUnit.INCH,result.getUnit());
   }
// Explicit Target Unit: YARDS
   @Test
   void testAddition_ExplicitTargetUnit_Yards() {
       QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
       QuantityLength b = new QuantityLength(12.0,LengthUnit.INCH);
       
       QuantityLength result = a.add(b,LengthUnit.YARDS);
       assertEquals(2.0 / 3.0,result.getValue(),1e-6);
       assertEquals(LengthUnit.YARDS,result.getUnit());
   }
//  Explicit Target Unit: CENTIMETERS
   @Test
   void testAddition_ExplicitTargetUnit_Centimeters() {
       QuantityLength a = new QuantityLength(1.0, LengthUnit.INCH);
       QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

       QuantityLength result = a.add(b, LengthUnit.CENTIMETERS);

       // 1 INCH + 1 INCH = 2 INCHES -> 2 * 2.54 = 5.08 CM
       assertEquals(5.08, result.getValue(), 1e-6);
       assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
   }
   //  Target Same As First Operand
   @Test
   void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
       QuantityLength a = new QuantityLength(2.0, LengthUnit.YARDS);
          QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
          
         QuantityLength result = a.add(b, LengthUnit.YARDS);
         assertEquals(3.0, result.getValue(), 1e-6);
         assertEquals(LengthUnit.YARDS, result.getUnit());
   }
//  Target Same As Second Operand
   @Test
   void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
       QuantityLength q1 = new QuantityLength(2.0, LengthUnit.YARDS);
       QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

       QuantityLength result = q1.add(q2, LengthUnit.FEET);

       assertEquals(9.0, result.getValue(),  1e-6);
       assertEquals(LengthUnit.FEET, result.getUnit());
   }

   //  Commutativity
   @Test
   void testAddition_ExplicitTargetUnit_Commutativity() {
       QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
       QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

       QuantityLength result1 = q1.add(q2, LengthUnit.YARDS);
       QuantityLength result2 = q2.add(q1, LengthUnit.YARDS);

       assertEquals(result1.getValue(), result2.getValue(),  1e-6);
       assertEquals(result1.getUnit(), result2.getUnit());
   }

   //  With Zero
   @Test
   void testAddition_ExplicitTargetUnit_WithZero() {
       QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
       QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

       QuantityLength result = q1.add(q2, LengthUnit.YARDS);

       assertEquals(1.6666667, result.getValue(), 1e-6);
       assertEquals(LengthUnit.YARDS, result.getUnit());
   }

   //  Negative Values
   @Test
   void testAddition_ExplicitTargetUnit_NegativeValues() {
       QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
       QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

       QuantityLength result = q1.add(q2, LengthUnit.INCH);

       assertEquals(36.0, result.getValue(), 1e-6);
       assertEquals(LengthUnit.INCH, result.getUnit());
   }

   //  Null Target Unit
   @Test
   void testAddition_ExplicitTargetUnit_NullTargetUnit() {
       QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
       QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

       assertThrows(IllegalArgumentException.class, () ->
               q1.add(q2, null));
   }

   //  Large to Small Scale
   @Test
   void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
       QuantityLength q1 = new QuantityLength(1000.0, LengthUnit.FEET);
       QuantityLength q2 = new QuantityLength(500.0, LengthUnit.FEET);

       QuantityLength result = q1.add(q2, LengthUnit.INCH);

       assertEquals(18000.0, result.getValue(), 1e-6);
       assertEquals(LengthUnit.INCH, result.getUnit());
   }

   // Small to Large Scale
   @Test
   void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
       QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
       QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

       QuantityLength result = q1.add(q2, LengthUnit.YARDS);

       assertEquals(0.6666667, result.getValue(),  1e-6);
       assertEquals(LengthUnit.YARDS, result.getUnit());
   }

   //  All Unit Combinations (Representative Coverage)
   @Test
   void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

       for (LengthUnit u1 : LengthUnit.values()) {
           for (LengthUnit u2 : LengthUnit.values()) {
               for (LengthUnit target : LengthUnit.values()) {

                   QuantityLength q1 = new QuantityLength(1.0, u1);
                   QuantityLength q2 = new QuantityLength(2.0, u2);

                   QuantityLength result = q1.add(q2, target);

                   assertEquals(target, result.getUnit());
               }
           }
       }
   }

   // Precision Tolerance
   @Test
   void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

       QuantityLength q1 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
       QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

       QuantityLength result = q1.add(q2, LengthUnit.CENTIMETERS);

       assertEquals(5.08, result.getValue(), 0.01);
   }
}



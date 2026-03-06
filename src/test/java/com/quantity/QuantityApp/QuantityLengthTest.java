package com.quantity.QuantityApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	private static final double EPSILON = 1e-6;

 //VolumeUnit test cases
 @Test
 void LitreToLitre_SameValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1.0,VolumeUnit.LITRE);
     assertTrue(v1.equals(v2));
 }
 @Test
 void LitreToLitre_DifferentValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(2.0,VolumeUnit.LITRE);
     assertFalse(v1.equals(v2));
 }
 @Test
 void LitreToMillilitre_EquivalentValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
     assertTrue(v1.equals(v2));
 }
 @Test
 void MillilitreToLitre_EquivalentValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1.0,VolumeUnit.LITRE);
     assertTrue(v1.equals(v2));
 }
 @Test
 void LitreToGallon_EquivalentValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(0.264172,VolumeUnit.GALLON);
     assertTrue(v1.equals(v2));
 }
 @Test
 void GallonToLitre_EquivalentValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
     Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
     assertTrue(v1.equals(v2));
 }
 @Test
 void VolumeVsLength_Incompatible() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
     Quantity<LengthUnit> v2 = new Quantity<>(1.0,LengthUnit.FEET);
     assertFalse(v1.equals(v2));
 }
 @Test
 void NullComparison() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
     assertFalse(v1.equals(null));
 }
 @Test
 void SameReference() {
     Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
     assertTrue(v.equals(v));
 }
 @Test
 void NullUnit() {
     assertThrows(IllegalArgumentException.class,
             () -> new Quantity<>(1.0, null));
 }
 @Test
 void ZeroValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(0.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
     assertTrue(v1.equals(v));
 }
 @Test
 void NegativeVolume() {
     Quantity<VolumeUnit> v1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
     assertTrue(v1.equals(v));
 }
 @Test
 void testEquality_LargeVolumeValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.LITRE);
     assertTrue(v1.equals(v2));
 }

 @Test
 void testEquality_SmallVolumeValue() {
     Quantity<VolumeUnit> v1 = new Quantity<>(0.001, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
     assertTrue(v1.equals(v2));
 }
 //Conversion Tests
 @Test
 void testConversion_LitreToMillilitre() {
     Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

     assertEquals(1000.0, result.getValue(), EPSILON);
 }

 @Test
 void testConversion_MillilitreToLitre() {
     Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

     assertEquals(1.0, result.getValue(), EPSILON);
 }

 @Test
 void testConversion_GallonToLitre() {
     Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

     assertEquals(3.78541, result.getValue(), EPSILON);
 }

 @Test
 void testConversion_LitreToGallon() {
     Quantity<VolumeUnit> v = new Quantity<>(3.78541, VolumeUnit.LITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

     assertEquals(1.0, result.getValue(), EPSILON);
 }

 @Test
 void testConversion_MillilitreToGallon() {
     Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

     assertEquals(0.264172, result.getValue(), EPSILON);
 }

 @Test
 void SameUnit() {
     Quantity<VolumeUnit> v = new Quantity<>(5.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

     assertEquals(5.0, result.getValue(), EPSILON);
 }

 @Test
 void ZeroValue2() {
     Quantity<VolumeUnit> v = new Quantity<>(0.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

     assertEquals(0.0, result.getValue(), EPSILON);
 }

 @Test
 void NegativeValue() {
     Quantity<VolumeUnit> v = new Quantity<>(-1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

     assertEquals(-1000.0, result.getValue(), EPSILON);
 }

 @Test
 void RoundTrip() {
     Quantity<VolumeUnit> v = new Quantity<>(1.5, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result =
             v.convertTo(VolumeUnit.MILLILITRE)
              .convertTo(VolumeUnit.LITRE);

     assertEquals(1.5, result.getValue(), EPSILON);
 }
 //Addition Tests
 @Test
 void testAddition_SameUnit_LitrePlusLitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(3.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_SameUnit_MillilitrePlusMillilitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(1000.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_CrossUnit_LitrePlusMillilitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(2.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_CrossUnit_MillilitrePlusLitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(2000.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_CrossUnit_GallonPlusLitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
     Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(2.0, result.getValue(), EPSILON);
 }
 //Addition With Target Unit
 @Test
 void testAddition_ExplicitTargetUnit_Litre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.LITRE);

     assertEquals(2.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_ExplicitTargetUnit_Millilitre() {
     Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

     assertEquals(2000.0, result.getValue(), EPSILON);
 }

 @Test
 void testAddition_ExplicitTargetUnit_Gallon() {
     Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
     Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);

     assertEquals(2.0, result.getValue(), EPSILON);
 }
 //Mathematical Property Tests
 @Test
 void Commutativity() {
     Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     assertTrue(a.add(b).equals(b.add(a)));
 }

 @Test
 void WithZero() {
     Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = a.add(zero);

     assertEquals(5.0, result.getValue(),EPSILON);
 }

 @Test
 void NegativeValues() {
     Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
     Quantity<VolumeUnit> b = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = a.add(b);

     assertEquals(3.0, result.getValue(),EPSILON);
 }

 @Test
 void LargeValues() {
     Quantity<VolumeUnit> a = new Quantity<>(1e6, VolumeUnit.LITRE);
     Quantity<VolumeUnit> b = new Quantity<>(1e6, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = a.add(b);

     assertEquals(2e6, result.getValue(),EPSILON);
 }

 @Test
 void SmallValues() {
     Quantity<VolumeUnit> a = new Quantity<>(0.001, VolumeUnit.LITRE);
     Quantity<VolumeUnit> b = new Quantity<>(0.002, VolumeUnit.LITRE);

     Quantity<VolumeUnit> result = a.add(b);

     assertEquals(0.003, result.getValue(), EPSILON);
 }
   
}



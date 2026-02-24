package com.quantity.QuantityApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
    	System.out.println("======== EQUALITY TESTS (UC4) ========");

    	 QuantityLength q1 =  new QuantityLength(1.0, LengthUnit.YARDS);
         QuantityLength q2 =  new QuantityLength(3.0, LengthUnit.FEET);

         System.out.println("Input: " + q1 + " and " + q2);
         System.out.println("Output: Equal (" + q1.equals(q2) + ")");

         QuantityLength q3 =  new QuantityLength(1.0, LengthUnit.YARDS);
         QuantityLength q4 =new QuantityLength(36.0, LengthUnit.INCH);

         System.out.println("Input: " + q3 + " and " + q4);
         System.out.println("Output: Equal (" + q3.equals(q4) + ")");

         QuantityLength q5 =  new QuantityLength(1.0, LengthUnit.CENTIMETERS);
         QuantityLength q6 =  new QuantityLength(0.393701, LengthUnit.INCH);

         System.out.println("Input: " + q5 + " and " + q6);
         System.out.println("Output: Equal (" + q5.equals(q6) + ")");
         
      // Static conversion calls
         System.out.println("\n======== STATIC CONVERSION TESTS (UC5) ========");

         System.out.println("1 FOOT to INCH = " +
                 QuantityLength.convert(1.0,
                         LengthUnit.FEET,
                         LengthUnit.INCH));

         System.out.println("3 YARDS to FEET = " +
                 QuantityLength.convert(3.0,
                         LengthUnit.YARDS,
                         LengthUnit.FEET));

         System.out.println("36 INCH to YARDS = " +
                 QuantityLength.convert(36.0,
                         LengthUnit.INCH,
                         LengthUnit.YARDS));

         System.out.println("2.54 CM to INCH = " +
                 QuantityLength.convert(2.54,
                         LengthUnit.CENTIMETERS,
                         LengthUnit.INCH));
         
         System.out.println("\n======== INSTANCE CONVERSION TESTS ========");

         QuantityLength length = new QuantityLength(5.0, LengthUnit.FEET);
         QuantityLength converted = length.convertTo(LengthUnit.INCH);

         System.out.println(length + " → " + converted);

         QuantityLength negativeLength = new QuantityLength(-2.0, LengthUnit.YARDS);
         System.out.println(negativeLength + " → " +
                 negativeLength.convertTo(LengthUnit.FEET));

         QuantityLength zeroLength = new QuantityLength(0.0, LengthUnit.FEET);
         System.out.println(zeroLength + " → " +
                 zeroLength.convertTo(LengthUnit.INCH));
    }
}


package com.quantity.QuantityApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

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
    }
}


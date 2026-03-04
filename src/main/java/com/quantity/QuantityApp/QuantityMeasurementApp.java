package com.quantity.QuantityApp;



public class QuantityMeasurementApp {

    public static void main(String[] args) {
    	 QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
         QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

         System.out.println(q1.convertTo(LengthUnit.INCH));
         System.out.println(q1.add(q2, LengthUnit.FEET));
         System.out.println(new QuantityLength(36.0, LengthUnit.INCH)
                 .equals(new QuantityLength(1.0, LengthUnit.YARDS)));  
     	
    }
}


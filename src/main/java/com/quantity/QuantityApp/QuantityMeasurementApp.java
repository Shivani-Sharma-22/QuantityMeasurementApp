package com.quantity.QuantityApp;



public class QuantityMeasurementApp {
	
	public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2 + " ? → " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q, U target) {

        System.out.println(q + " converted → " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U target) {

        System.out.println(q1 + " + " + q2 + " → " + q1.add(q2, target));
    }
    
    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U target) {

        System.out.println(q1 + " - " + q2 + " → " + q1.subtract(q2));
       
    }
    
    public static <U extends IMeasurable> void demonstrateDivide(
    		Quantity<U> q1,Quantity<U> q2,U target) {
       System.out.println(q1 + " / "+ q2 + " → " + q1.divide(q2));
    }
    public static void main(String[] args) {

        
    	Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(120.0, LengthUnit.INCH);

    	Quantity<LengthUnit> result = a.subtract(b);
    	System.out.println(result); 
    	
    	Quantity<LengthUnit> result2 = a.divide(b);
    	System.out.println(result2);
    	
        
        
    }
}


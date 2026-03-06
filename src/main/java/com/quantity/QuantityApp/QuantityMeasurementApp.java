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
    public static void main(String[] args) {

        
        System.out.println("\n===== VOLUME OPERATIONS =====");
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        demonstrateEquality(v1, v2);
        
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v4 = new Quantity<>(1.0,VolumeUnit.LITRE);
        demonstrateEquality(v3, v4);
        
        Quantity<VolumeUnit> v5 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v6 = new Quantity<>(2.0,VolumeUnit.LITRE);
        demonstrateEquality(v1, v2);
        demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        demonstrateAddition(v5, v6, VolumeUnit.LITRE);
        
        System.out.println("\n===== TARGET UNIT ADDITION CHECK =====");

        Quantity<VolumeUnit> v7 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v8 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        // Target unit = MILLILITRE
        Quantity<VolumeUnit> result = v7.add(v8, VolumeUnit.MILLILITRE);

        System.out.println(v7 + " + " + v8 + " in MILLILITRE → " + result);
        
        
    }
}


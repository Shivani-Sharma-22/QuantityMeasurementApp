package com.quantity.QuantityApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        System.out.println("\n============Quantiy Length Api to add to target length=========");
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        // UC6 style
        System.out.println("UC6-style");
        System.out.println(l1.add(l2)); 
       

        // UC7 style
        System.out.println(l1.add(l2, LengthUnit.INCH)); 
       

        System.out.println(l1.add(l2, LengthUnit.YARDS));
    }
}


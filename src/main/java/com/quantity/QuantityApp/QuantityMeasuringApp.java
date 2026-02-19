package com.quantity.QuantityApp;

public class QuantityMeasuringApp {
	
   public static class Feet{
	   private final double value;
	   
	   public Feet(double value) {
		   this.value=value;
	   }
	   
	  

	   public Feet(Object object) {
		this.value = 0;
		// TODO Auto-generated constructor stub
	}



	   @Override
	   public boolean equals(Object obj) {
		    
		   if(this==obj) {
			   return true;
		   }
		   
		   if(obj==null) {
			   return false;
		   }
		   
		   if(getClass()!=obj.getClass()) {
			   return false;
		   }
		   
		   Feet f=(Feet)obj;
		   
		   return Double.compare(this.value, f.value)==0;
	   }
	   
	   @Override
	   public int hashCode() {
		   return Double.hashCode(value);
	   }
   }
   
   public static void main(String[]args) {
	   
	   Feet v1=new Feet(1.0);
	   Feet v2=new Feet(null);
	   
	   boolean result=v1.equals(v2);
	   
	   System.out.println("both are : "+result);
	   
   }
}
package com.quantity.QuantityApp;

public enum ArithmeticOperation {
	ADD{
		public double compute(double thisBase,double otherBase) {
			return thisBase + otherBase;
		}
	},
	SUBTRACT{
		public double compute(double thisBase,double otherBase) {
			return thisBase - otherBase;
		}
	},
	DIVIDE{
		public double compute(double thisBase,double otherBase) {
			if(Math.abs(otherBase)< 1e-9) {
				throw new ArithmeticException("Can not divide by zero");
			}
			return thisBase/otherBase;
		}
	};
	public abstract double compute(double thisBase,double otherBase);
	
}

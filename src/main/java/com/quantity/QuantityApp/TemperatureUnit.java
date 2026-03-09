package com.quantity.QuantityApp;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value; // Base unit is Celsius
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value;
        }

        @Override
        public void validateOperationSupport(String operation) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operation"
            );
        }
        @Override
        public boolean supportsAddition() { return false; }
        @Override
        public boolean supportsSubtraction() { return false; }
        @Override
        public boolean supportsDivision() { return false; }

        @Override
        public double getConversionFactor() {
            return 1.0; // For consistency, Celsius is base
        }

        @Override
        public String getUnitName() {
            return "Celsius";
        }
    },

    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9; // F → C
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value * 9 / 5 + 32; // C → F
        }

        @Override
        public void validateOperationSupport(String operation) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operation"
            );
        }
        @Override
        public boolean supportsAddition() { return false; }
        @Override
        public boolean supportsSubtraction() { return false; }
        @Override
        public boolean supportsDivision() { return false; }

        @Override
        public double getConversionFactor() {
            return 1.0; // Conversion factor relative to Celsius
        }

        @Override
        public String getUnitName() {
            return "Fahrenheit";
        }
    },

    KELVIN {
        @Override
        public double convertToBaseUnit(double value) {
            return value - 273.15; // K → C
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value + 273.15; // C → K
        }

        @Override
        public void validateOperationSupport(String operation) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operation"
            );
        }

        @Override
        public boolean supportsAddition() { return false; }
        @Override
        public boolean supportsSubtraction() { return false; }
        @Override
        public boolean supportsDivision() { return false; }
        @Override
        public double getConversionFactor() {
            return 1.0; // Same as Celsius
        }

        @Override
        public String getUnitName() {
            return "Kelvin";
        }
    };
}
package com.qm.quantitymeasurement.enums;

public enum Unit {
    FEET(12),
    INCH(1);

    private final double conversionFactor;

    Unit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
}

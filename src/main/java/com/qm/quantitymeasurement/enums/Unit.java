package com.qm.quantitymeasurement.enums;

public enum Unit {
    FEET(30.48),
    INCH(2.54),
    YARD(91.44),
    CENTIMETER(1);


    private final double conversionFactor;

    Unit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

}

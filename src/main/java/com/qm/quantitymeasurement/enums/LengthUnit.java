package com.qm.quantitymeasurement.enums;

import com.qm.quantitymeasurement.contracts.IMeasurable;

public enum LengthUnit implements IMeasurable {
    FEET(30.48),
    INCH(2.54),
    YARD(91.44),
    CENTIMETER(1);


    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(
            double baseValue
    ) {
        return baseValue / conversionFactor;
    }

    @Override
    public MeasurementType getMeasurementType() {
        return MeasurementType.LENGTH;
    }

    @Override
    public IMeasurable getBaseUnit() {
        return CENTIMETER;
    }

}

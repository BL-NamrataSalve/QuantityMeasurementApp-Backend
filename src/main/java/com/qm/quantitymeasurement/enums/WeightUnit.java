package com.qm.quantitymeasurement.enums;

import com.qm.quantitymeasurement.contracts.IMeasurable;

public enum WeightUnit implements IMeasurable {
    GRAM(1),
    KILOGRAM(1000),
    TONNE(1000000);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
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
        return MeasurementType.WEIGHT;
    }

    @Override
    public IMeasurable getBaseUnit() {
        return GRAM;
    }
}

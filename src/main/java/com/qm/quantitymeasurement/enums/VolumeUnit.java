package com.qm.quantitymeasurement.enums;

import com.qm.quantitymeasurement.contracts.IMeasurable;

public enum VolumeUnit implements IMeasurable {

    MILLILITRE(1),
    LITRE(1000),
    GALLON(3785.41);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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
        return MeasurementType.VOLUME;
    }

    @Override
    public IMeasurable getBaseUnit() {
        return MILLILITRE;
    }

}

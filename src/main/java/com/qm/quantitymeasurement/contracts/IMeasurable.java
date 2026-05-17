package com.qm.quantitymeasurement.contracts;

import com.qm.quantitymeasurement.enums.MeasurementType;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    MeasurementType getMeasurementType();
    IMeasurable getBaseUnit();
}

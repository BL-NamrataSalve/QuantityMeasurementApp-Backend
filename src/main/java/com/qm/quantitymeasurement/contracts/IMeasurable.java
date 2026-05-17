package com.qm.quantitymeasurement.contracts;

public interface IMeasurable {
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);
}

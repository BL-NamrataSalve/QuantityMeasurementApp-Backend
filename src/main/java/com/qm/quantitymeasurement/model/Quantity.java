package com.qm.quantitymeasurement.model;

import com.qm.quantitymeasurement.enums.Unit;

import java.util.Objects;

public class Quantity {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final Unit unit;

    public Quantity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    private double getValueInBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity convertTo(Unit targetUnit) {

        double baseValue = getValueInBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Quantity quantity = (Quantity) object;

        return Math.abs(
                this.getValueInBaseUnit()
                        - quantity.getValueInBaseUnit()
        ) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
    @Override
    public String toString() {
        return value + " " + unit;
    }

}

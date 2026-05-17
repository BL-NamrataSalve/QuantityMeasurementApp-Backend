package com.qm.quantitymeasurement.model;

import com.qm.quantitymeasurement.enums.Unit;

import java.util.Objects;

public class Quantity {

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
        ) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

}

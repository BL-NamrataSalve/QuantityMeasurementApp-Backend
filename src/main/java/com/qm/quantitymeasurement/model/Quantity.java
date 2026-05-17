package com.qm.quantitymeasurement.model;

import com.qm.quantitymeasurement.contracts.IMeasurable;
import com.qm.quantitymeasurement.enums.LengthUnit;

import java.util.Objects;

//import static java.lang.Math.round;

public class Quantity {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final IMeasurable unit;

    public Quantity(double value, IMeasurable unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    public Quantity convertTo(IMeasurable targetUnit) {

        double baseValue = getValueInBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(convertedValue, targetUnit);
    }

    public Quantity add(Quantity other) {
        return add(other, LengthUnit.CENTIMETER);
    }

    public Quantity add(Quantity other, IMeasurable targetUnit
    ) {
        validateQuantity(other);

        double totalBaseValue =
                addBaseValues(other);

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        totalBaseValue
                );

        return new Quantity(
                roundValue(convertedValue),
                targetUnit
        );
    }
    private double addBaseValues(
            Quantity other
    ) {

        return this.getValueInBaseUnit()
                + other.getValueInBaseUnit();
    }

    private double getValueInBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private void validate(
            double value,
            IMeasurable unit
    ) {

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Value cannot be negative"
            );
        }
    }

        private void validateQuantity(Quantity quantity) {

            if (quantity == null) {
                throw new IllegalArgumentException(
                        "Quantity cannot be null"
                );
            }

            if (this.unit.getMeasurementType()
                    != quantity.unit.getMeasurementType()) {

                throw new IllegalArgumentException(
                        "Different measurement categories"
                );
            }
        }

    private double roundValue(double value) {
        return Math.round(value * 100.0) / 100.0;
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

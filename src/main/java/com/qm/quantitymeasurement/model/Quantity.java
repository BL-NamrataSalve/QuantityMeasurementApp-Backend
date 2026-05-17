package com.qm.quantitymeasurement.model;

import com.qm.quantitymeasurement.contracts.IMeasurable;
import com.qm.quantitymeasurement.enums.LengthUnit;

import java.util.Objects;

//import static java.lang.Math.round;

public class Quantity<T extends IMeasurable>{

    private static final double EPSILON = 0.0001;

    private final double value;
    private final T unit;

    public Quantity(double value, T unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }

    public Quantity<T> convertTo(T targetUnit) {

        double baseValue = getValueInBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(
                roundValue(convertedValue),
                targetUnit
        );
    }

    public Quantity<T> add(
            Quantity<T> other
    ) {

        validateQuantity(other);

        double totalBaseValue =
                addBaseValues(other);

        T baseUnit = (T) unit.getBaseUnit();

        return new Quantity<>(
                roundValue(totalBaseValue),
                baseUnit
        );
    }

    public Quantity<T> add(
            Quantity<T> other,
            T targetUnit
    ) {
        validateQuantity(other);

        double totalBaseValue =
                addBaseValues(other);

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        totalBaseValue
                );

        return new Quantity<>(roundValue(convertedValue),
                targetUnit
        );
    }
    private double addBaseValues(
            Quantity<T> other
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

        private void validateQuantity( Quantity<T> quantity) {

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

        Quantity<?> quantity =
                (Quantity<?>) object;

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

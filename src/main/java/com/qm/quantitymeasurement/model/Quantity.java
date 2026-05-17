package com.qm.quantitymeasurement.model;

import com.qm.quantitymeasurement.contracts.IMeasurable;
import com.qm.quantitymeasurement.enums.LengthUnit;
import com.qm.quantitymeasurement.operations.OperationType;

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

        T baseUnit =
                (T) unit.getBaseUnit();

        return add(other, baseUnit);
    }

    public Quantity<T> add(
            Quantity<T> other,
            T targetUnit
    ) {

        double resultBaseValue =
                performOperation(
                        other,
                        OperationType.ADDITION
                );

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        resultBaseValue
                );

        return new Quantity<>(
                roundValue(convertedValue),
                targetUnit
        );
    }

    public Quantity<T> subtract(
            Quantity<T> other
    ) {

        T baseUnit =
                (T) unit.getBaseUnit();

        return subtract(other, baseUnit);
    }

    public Quantity<T> subtract(
            Quantity<T> other,
            T targetUnit
    ) {

        double resultBaseValue =
                performOperation(
                        other,
                        OperationType.SUBTRACTION
                );

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        resultBaseValue
                );

        return new Quantity<>(
                roundValue(convertedValue),
                targetUnit
        );
    }

    public Quantity<T> divide(
            double divisor
    ) {

        validateDivision(divisor);

        return new Quantity<>(
                roundValue(value / divisor),
                unit
        );
    }

    public Quantity<T> divide(
            double divisor,
            T targetUnit
    ) {

        validateDivision(divisor);

        return this.divide(divisor)
                .convertTo(targetUnit);
    }

    private double getValueInBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private double performOperation(
            Quantity<T> other,
            OperationType operationType
    ) {

        validateQuantity(other);

        return operationType.apply(
                this.getValueInBaseUnit(),
                other.getValueInBaseUnit()
        );
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

    private void validateDivision(
            double divisor
    ) {

        if (divisor == 0) {
            throw new IllegalArgumentException(
                    "Division by zero is not allowed"
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

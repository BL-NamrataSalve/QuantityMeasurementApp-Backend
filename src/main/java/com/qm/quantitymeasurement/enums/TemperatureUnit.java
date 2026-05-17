package com.qm.quantitymeasurement.enums;

import com.qm.quantitymeasurement.contracts.IMeasurable;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS {

        @Override
        public double convertToBaseUnit(
                double value
        ) {
            return value;
        }

        @Override
        public double convertFromBaseUnit(
                double baseValue
        ) {
            return baseValue;
        }
    },

    FAHRENHEIT {

        @Override
        public double convertToBaseUnit(
                double value
        ) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double convertFromBaseUnit(
                double baseValue
        ) {
            return (baseValue * 9 / 5) + 32;
        }
    };

    @Override
    public MeasurementType getMeasurementType() {
        return MeasurementType.TEMPERATURE;
    }

    @Override
    public IMeasurable getBaseUnit() {
        return CELSIUS;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }
}

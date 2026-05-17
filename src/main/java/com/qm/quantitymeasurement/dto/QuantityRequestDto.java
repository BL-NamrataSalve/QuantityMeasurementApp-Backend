package com.qm.quantitymeasurement.dto;

public class QuantityRequestDto {
    private final double value;
    private final String unit;

    public QuantityRequestDto(
            double value,
            String unit
    ) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}

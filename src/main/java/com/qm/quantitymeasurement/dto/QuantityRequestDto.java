package com.qm.quantitymeasurement.dto;

public class QuantityRequestDto {
    private Double value;
    private String unit;

    private Double firstValue;
    private String firstUnit;

    private Double secondValue;
    private String secondUnit;

    private String targetUnit;
    private Double divisor;
    private String operation;

    public QuantityRequestDto() {}

    public QuantityRequestDto(Double value, String unit, Double firstValue, String firstUnit, Double secondValue, String secondUnit, String targetUnit, Double divisor, String operation) {
        this.value = value;
        this.unit = unit;
        this.firstValue = firstValue;
        this.firstUnit = firstUnit;
        this.secondValue = secondValue;
        this.secondUnit = secondUnit;
        this.targetUnit = targetUnit;
        this.divisor = divisor;
        this.operation = operation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Double firstValue) {
        this.firstValue = firstValue;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public Double getDivisor() {
        return divisor;
    }

    public void setDivisor(Double divisor) {
        this.divisor = divisor;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "QuantityRequestDto{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", firstValue=" + firstValue +
                ", firstUnit='" + firstUnit + '\'' +
                ", secondValue=" + secondValue +
                ", secondUnit='" + secondUnit + '\'' +
                ", targetUnit='" + targetUnit + '\'' +
                ", divisor=" + divisor +
                ", operation='" + operation + '\'' +
                '}';
    }
}

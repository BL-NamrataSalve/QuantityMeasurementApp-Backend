package com.qm.quantitymeasurement.dto;

public class QuantityResponseDto {
    private double value;
    private String unit;
    private String category;

    public QuantityResponseDto() {}

    public QuantityResponseDto(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public QuantityResponseDto(double value, String unit, String category) {
        this.value = value;
        this.unit = unit;
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "QuantityResponseDto{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

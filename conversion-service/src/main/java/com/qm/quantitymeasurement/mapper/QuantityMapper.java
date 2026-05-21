package com.qm.quantitymeasurement.mapper;

import com.qm.quantitymeasurement.contracts.IMeasurable;
import com.qm.quantitymeasurement.dto.QuantityDTO;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.enums.LengthUnit;
import com.qm.quantitymeasurement.enums.TemperatureUnit;
import com.qm.quantitymeasurement.enums.VolumeUnit;
import com.qm.quantitymeasurement.enums.WeightUnit;
import com.qm.quantitymeasurement.model.Quantity;

public class QuantityMapper {
    private QuantityMapper() {
    }

    public static IMeasurable parseUnit(String unitStr) {
        if (unitStr == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        String upperUnit = unitStr.toUpperCase().trim();
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.name().equals(upperUnit)) return unit;
        }
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.name().equals(upperUnit)) return unit;
        }
        for (VolumeUnit unit : VolumeUnit.values()) {
            if (unit.name().equals(upperUnit)) return unit;
        }
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.name().equals(upperUnit)) return unit;
        }
        throw new IllegalArgumentException("Invalid unit: " + unitStr);
    }

    public static Quantity<IMeasurable> toQuantity(QuantityDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Quantity request cannot be null");
        }
        IMeasurable unit = parseUnit(dto.getUnit());
        return new Quantity<>(dto.getValue(), unit);
    }

    public static QuantityResponseDto toResponseDto(Quantity<?> quantity) {
        return new QuantityResponseDto(
                quantity.getValue(),
                quantity.getUnit().toString()
        );
    }
}

package com.qm.quantitymeasurement.mapper;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.enums.LengthUnit;
import com.qm.quantitymeasurement.model.Quantity;

public class QuantityMapper {
    private QuantityMapper() {
    }

    public static Quantity<LengthUnit>
    toLengthQuantity(
            QuantityRequestDto dto
    ) {

        return new Quantity<>(
                dto.getValue(),
                LengthUnit.valueOf(
                        dto.getUnit()
                )
        );
    }

    public static QuantityResponseDto
    toResponseDto(
            Quantity<?> quantity
    ) {

        return new QuantityResponseDto(
                quantity.getValue(),
                quantity.getUnit().toString()
        );
    }
}

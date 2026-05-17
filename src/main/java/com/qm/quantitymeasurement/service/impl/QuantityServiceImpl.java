package com.qm.quantitymeasurement.service.impl;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.enums.LengthUnit;
import com.qm.quantitymeasurement.mapper.QuantityMapper;
import com.qm.quantitymeasurement.model.Quantity;
import com.qm.quantitymeasurement.service.QuantityService;

public class QuantityServiceImpl implements QuantityService {

    @Override
    public QuantityResponseDto add(
            QuantityRequestDto first,
            QuantityRequestDto second
    ) {

        Quantity<LengthUnit> firstQuantity =
                QuantityMapper.toLengthQuantity(first);

        Quantity<LengthUnit> secondQuantity =
                QuantityMapper.toLengthQuantity(second);

        Quantity<LengthUnit> result =
                firstQuantity.add(secondQuantity);

        return QuantityMapper.toResponseDto(result);
    }

    @Override
    public QuantityResponseDto subtract(
            QuantityRequestDto first,
            QuantityRequestDto second
    ) {

        Quantity<LengthUnit> firstQuantity =
                QuantityMapper.toLengthQuantity(first);

        Quantity<LengthUnit> secondQuantity =
                QuantityMapper.toLengthQuantity(second);

        Quantity<LengthUnit> result =
                firstQuantity.subtract(secondQuantity);

        return QuantityMapper.toResponseDto(result);
    }

}

package com.qm.quantitymeasurement.controller;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.factory.ServiceFactory;
import com.qm.quantitymeasurement.service.QuantityService;

public class QuantityController {
    public static void main(String[] args) {

        QuantityService service =
                ServiceFactory.createQuantityService();

        QuantityRequestDto first =
                new QuantityRequestDto(
                        1,
                        "FEET"
                );

        QuantityRequestDto second =
                new QuantityRequestDto(
                        12,
                        "INCH"
                );

        QuantityResponseDto result =
                service.add(first, second);

        System.out.println(
                result.getValue()
                        + " "
                        + result.getUnit()
        );
    }
}

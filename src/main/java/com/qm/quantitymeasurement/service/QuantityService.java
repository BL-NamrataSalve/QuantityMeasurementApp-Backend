package com.qm.quantitymeasurement.service;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;

public interface QuantityService {
    QuantityResponseDto add(
            QuantityRequestDto first,
            QuantityRequestDto second
    );

    QuantityResponseDto subtract(
            QuantityRequestDto first,
            QuantityRequestDto second
    );
}

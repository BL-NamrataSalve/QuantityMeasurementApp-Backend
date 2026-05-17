package com.qm.quantitymeasurement.repository;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;

public interface QuantityRepository {
    void saveOperation(
            QuantityRequestDto first,
            QuantityRequestDto second,
            String operationType,
            QuantityResponseDto result
    );
}

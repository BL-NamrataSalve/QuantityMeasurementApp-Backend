package com.qm.quantitymeasurement.service;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.entity.QuantityOperationEntity;
import java.util.List;

public interface QuantityService {
    QuantityResponseDto add(QuantityRequestDto input);
    QuantityResponseDto subtract(QuantityRequestDto input);
    Double divide(QuantityRequestDto input);
    QuantityResponseDto convert(QuantityRequestDto input);
    QuantityResponseDto compare(QuantityRequestDto input);
    List<QuantityOperationEntity> getHistory();
    List<QuantityOperationEntity> getByOperation(String operation);
}

package com.qm.quantitymeasurement.service.impl;

import com.qm.quantitymeasurement.contracts.IMeasurable;
import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.entity.QuantityOperationEntity;
import com.qm.quantitymeasurement.mapper.QuantityMapper;
import com.qm.quantitymeasurement.model.Quantity;
import com.qm.quantitymeasurement.repository.QuantityRepository;
import com.qm.quantitymeasurement.service.QuantityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuantityServiceImpl implements QuantityService {

    private static final Logger log = LoggerFactory.getLogger(QuantityServiceImpl.class);
    private final QuantityRepository repository;
    @Autowired
    public QuantityServiceImpl(QuantityRepository repository) {
        this.repository = repository;
    }

    private String getAuthenticatedUserEmail() {
        return "anonymous"; // Could be extracted from HTTP headers injected by Gateway
    }

    @Override
    public QuantityResponseDto add(QuantityRequestDto input) {
        log.info("Add operation called with input: {}", input);
        try {
            double val1 = input.getFirstValue() != null ? input.getFirstValue() : (input.getValue() != null ? input.getValue() : 0.0);
            String u1 = input.getFirstUnit() != null ? input.getFirstUnit() : input.getUnit();
            double val2 = input.getSecondValue() != null ? input.getSecondValue() : 0.0;
            String u2 = input.getSecondUnit();

            Quantity<IMeasurable> q1 = new Quantity<>(val1, QuantityMapper.parseUnit(u1));
            Quantity<IMeasurable> q2 = new Quantity<>(val2, QuantityMapper.parseUnit(u2));

            Quantity<IMeasurable> result;
            if (input.getTargetUnit() != null && !input.getTargetUnit().isEmpty()) {
                result = q1.add(q2, QuantityMapper.parseUnit(input.getTargetUnit()));
            } else {
                result = q1.add(q2);
            }
            log.info("Addition successful. Result: {}", result);

            QuantityOperationEntity entity = new QuantityOperationEntity(
                    val1, u1, val2, u2, "ADDITION", result.getValue(), result.getUnit().toString()
            );
            entity.setUserEmail(getAuthenticatedUserEmail());
            repository.save(entity);

            return new QuantityResponseDto(result.getValue(), result.getUnit().toString(), q1.getUnit().getMeasurementType().name());
        } catch (Exception e) {
            log.error("Failed to perform addition: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public QuantityResponseDto subtract(QuantityRequestDto input) {
        log.info("Subtract operation called with input: {}", input);
        try {
            double val1 = input.getFirstValue() != null ? input.getFirstValue() : (input.getValue() != null ? input.getValue() : 0.0);
            String u1 = input.getFirstUnit() != null ? input.getFirstUnit() : input.getUnit();
            double val2 = input.getSecondValue() != null ? input.getSecondValue() : 0.0;
            String u2 = input.getSecondUnit();

            Quantity<IMeasurable> q1 = new Quantity<>(val1, QuantityMapper.parseUnit(u1));
            Quantity<IMeasurable> q2 = new Quantity<>(val2, QuantityMapper.parseUnit(u2));

            Quantity<IMeasurable> result;
            if (input.getTargetUnit() != null && !input.getTargetUnit().isEmpty()) {
                result = q1.subtract(q2, QuantityMapper.parseUnit(input.getTargetUnit()));
            } else {
                result = q1.subtract(q2);
            }
            log.info("Subtraction successful. Result: {}", result);

            QuantityOperationEntity entity = new QuantityOperationEntity(
                    val1, u1, val2, u2, "SUBTRACTION", result.getValue(), result.getUnit().toString()
            );
            entity.setUserEmail(getAuthenticatedUserEmail());
            repository.save(entity);

            return new QuantityResponseDto(result.getValue(), result.getUnit().toString(), q1.getUnit().getMeasurementType().name());
        } catch (Exception e) {
            log.error("Failed to perform subtraction: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Double divide(QuantityRequestDto input) {
        log.info("Divide operation called with input: {}", input);
        try {
            double val1 = input.getFirstValue() != null ? input.getFirstValue() : (input.getValue() != null ? input.getValue() : 0.0);
            String u1 = input.getFirstUnit() != null ? input.getFirstUnit() : input.getUnit();
            double divisor = input.getDivisor() != null ? input.getDivisor() : (input.getSecondValue() != null ? input.getSecondValue() : 1.0);
            
            if (input.getSecondUnit() != null && !input.getSecondUnit().isEmpty() && !input.getSecondUnit().equalsIgnoreCase("N/A")) {
                Quantity<IMeasurable> divisorQty = new Quantity<>(divisor, QuantityMapper.parseUnit(input.getSecondUnit()));
                Quantity<IMeasurable> convertedDivisor = divisorQty.convertTo(QuantityMapper.parseUnit(u1));
                divisor = convertedDivisor.getValue();
            }

            Quantity<IMeasurable> q1 = new Quantity<>(val1, QuantityMapper.parseUnit(u1));
            Quantity<IMeasurable> result = q1.divide(divisor);
            log.info("Division successful. Result: {}", result);
            QuantityOperationEntity entity = new QuantityOperationEntity(
                    val1, u1, divisor, "DIVISOR", "DIVISION", result.getValue(), result.getUnit().toString()
            );
            entity.setUserEmail(getAuthenticatedUserEmail());
            repository.save(entity);
            return result.getValue();
        } catch (Exception e) {
            log.error("Failed to perform division: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public QuantityResponseDto convert(QuantityRequestDto input) {
        log.info("Convert operation called with input: {}", input);
        try {
            double val = input.getValue() != null ? input.getValue() : (input.getFirstValue() != null ? input.getFirstValue() : 0.0);
            String u = input.getUnit() != null ? input.getUnit() : input.getFirstUnit();
            String targetUnit = input.getTargetUnit();

            Quantity<IMeasurable> q = new Quantity<>(val, QuantityMapper.parseUnit(u));
            IMeasurable target = QuantityMapper.parseUnit(targetUnit);

            if (q.getUnit().getMeasurementType() != target.getMeasurementType()) {
                throw new IllegalArgumentException("Cannot convert between different categories: "
                        + q.getUnit().getMeasurementType() + " and " + target.getMeasurementType());
            }

            Quantity<IMeasurable> result = q.convertTo(target);
            log.info("Conversion successful. Result: {}", result);

            QuantityOperationEntity entity = new QuantityOperationEntity(
                    val, u, 0.0, targetUnit, "CONVERSION", result.getValue(), result.getUnit().toString()
            );
            entity.setUserEmail(getAuthenticatedUserEmail());
            repository.save(entity);

            return new QuantityResponseDto(result.getValue(), result.getUnit().toString(), q.getUnit().getMeasurementType().name());
        } catch (Exception e) {
            log.error("Failed to perform conversion: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public QuantityResponseDto compare(QuantityRequestDto input) {
        log.info("Compare operation called with input: {}", input);
        try {
            double val1 = input.getFirstValue() != null ? input.getFirstValue() : (input.getValue() != null ? input.getValue() : 0.0);
            String u1 = input.getFirstUnit() != null ? input.getFirstUnit() : input.getUnit();
            double val2 = input.getSecondValue() != null ? input.getSecondValue() : 0.0;
            String u2 = input.getSecondUnit();

            Quantity<IMeasurable> q1 = new Quantity<>(val1, QuantityMapper.parseUnit(u1));
            Quantity<IMeasurable> q2 = new Quantity<>(val2, QuantityMapper.parseUnit(u2));

            if (q1.getUnit().getMeasurementType() != q2.getUnit().getMeasurementType()) {
                throw new IllegalArgumentException("Cannot compare different categories: "
                        + q1.getUnit().getMeasurementType() + " and " + q2.getUnit().getMeasurementType());
            }

            boolean isEqual = q1.equals(q2);
            log.info("Comparison successful. Is equal: {}", isEqual);

            QuantityOperationEntity entity = new QuantityOperationEntity(
                    val1, u1, val2, u2, "COMPARISON", isEqual ? 1.0 : 0.0, isEqual ? "EQUAL" : "NOT_EQUAL"
            );
            entity.setUserEmail(getAuthenticatedUserEmail());
            repository.save(entity);
            return new QuantityResponseDto(isEqual ? 1.0 : 0.0, isEqual ? "EQUAL" : "NOT_EQUAL", q1.getUnit().getMeasurementType().name());
        } catch (Exception e) {
            log.error("Failed to perform comparison: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<QuantityOperationEntity> getHistory() {
        log.info("Fetching all history records.");
        String userEmail = getAuthenticatedUserEmail();
        if (userEmail != null && !userEmail.equals("anonymous")) {
            return repository.findByUserEmail(userEmail);
        }
        return repository.findAll();
    }

    @Override
    public List<QuantityOperationEntity> getByOperation(String operation) {
        log.info("Fetching history records for operation: {}", operation);
        String userEmail = getAuthenticatedUserEmail();
        if (userEmail != null && !userEmail.equals("anonymous")) {
            return repository.findByUserEmailAndOperationType(userEmail, operation);
        }
        return repository.findByOperationType(operation);
    }
}
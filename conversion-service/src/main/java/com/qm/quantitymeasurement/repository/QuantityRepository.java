package com.qm.quantitymeasurement.repository;

import com.qm.quantitymeasurement.entity.QuantityOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuantityRepository extends JpaRepository<QuantityOperationEntity, Long> {
    List<QuantityOperationEntity> findByOperationType(String operationType);
    List<QuantityOperationEntity> findByUserEmail(String userEmail);
    List<QuantityOperationEntity> findByUserEmailAndOperationType(String userEmail, String operationType);
}

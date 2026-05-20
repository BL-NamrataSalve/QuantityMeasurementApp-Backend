package com.qm.quantitymeasurement.repository;

import com.qm.quantitymeasurement.entity.QuantityOperationEntity;
import com.qm.quantitymeasurement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuantityRepository extends JpaRepository<QuantityOperationEntity, Long> {
    List<QuantityOperationEntity> findByOperationType(String operationType);
    List<QuantityOperationEntity> findByUser(UserEntity user);
    List<QuantityOperationEntity> findByUserAndOperationType(UserEntity user, String operationType);
}

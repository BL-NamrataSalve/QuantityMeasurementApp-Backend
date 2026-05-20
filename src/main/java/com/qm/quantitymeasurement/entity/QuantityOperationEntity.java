package com.qm.quantitymeasurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_operations")
public class QuantityOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double firstQuantityValue;
    private String firstUnit;
    private double secondQuantityValue;
    private String secondUnit;
    private String operationType;
    private double resultQuantityValue;
    private String resultUnit;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public QuantityOperationEntity() {}

    public QuantityOperationEntity(
            double firstQuantityValue,
            String firstUnit,
            double secondQuantityValue,
            String secondUnit,
            String operationType,
            double resultQuantityValue,
            String resultUnit
    ) {
        this.firstQuantityValue = firstQuantityValue;
        this.firstUnit = firstUnit;
        this.secondQuantityValue = secondQuantityValue;
        this.secondUnit = secondUnit;
        this.operationType = operationType;
        this.resultQuantityValue = resultQuantityValue;
        this.resultUnit = resultUnit;
    }

    public Long getId() { 
        return id; 
    }

    public double getFirstQuantityValue() { 
        return firstQuantityValue; 
    }

    public void setFirstQuantityValue(double firstQuantityValue) {
        this.firstQuantityValue = firstQuantityValue;
    }

    public String getFirstUnit() { 
        return firstUnit; 
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public double getSecondQuantityValue() { 
        return secondQuantityValue; 
    }

    public void setSecondQuantityValue(double secondQuantityValue) {
        this.secondQuantityValue = secondQuantityValue;
    }

    public String getSecondUnit() { 
        return secondUnit; 
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public String getOperationType() { 
        return operationType; 
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getResultQuantityValue() { 
        return resultQuantityValue; 
    }

    public void setResultQuantityValue(double resultQuantityValue) {
        this.resultQuantityValue = resultQuantityValue;
    }

    public String getResultUnit() { 
        return resultUnit; 
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

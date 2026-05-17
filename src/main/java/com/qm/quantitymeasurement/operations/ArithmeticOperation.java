package com.qm.quantitymeasurement.operations;

@FunctionalInterface
public interface ArithmeticOperation {
    double apply(double firstValue,
                 double secondValue);
}

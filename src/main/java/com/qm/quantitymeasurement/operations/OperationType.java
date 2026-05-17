package com.qm.quantitymeasurement.operations;

public enum OperationType {
    ADDITION((first, second)
            -> first + second),

    SUBTRACTION((first, second)
            -> first - second);

    private final ArithmeticOperation operation;

    OperationType(
            ArithmeticOperation operation
    ) {
        this.operation = operation;
    }

    public double apply(
            double firstValue,
            double secondValue
    ) {
        return operation.apply(
                firstValue,
                secondValue
        );
    }

}

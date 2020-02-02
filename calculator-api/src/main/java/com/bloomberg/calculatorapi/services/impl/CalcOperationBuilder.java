package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.services.CalcOperation;

public class CalcOperationBuilder {

    public static CalcOperation build(OperationType operationType) {
        CalcOperation calcOperation = null;
        if (operationType == null) {
            throw new IllegalArgumentException("Operation type must be informed");
        }
        switch (operationType) {
            case ADDICTION:
                calcOperation = new CalcOperationAddictionStrategy();
                break;
            case SUBTRACTION:
                calcOperation = new CalcOperationSubtractionStrategy();
                break;
            case MULTIPLICATION:
                calcOperation = new CalcOperationMultiplicationStrategy();
                break;
            case DIVISION:
                calcOperation = new CalcOperationDivisionStrategy();
                break;
        }
        return calcOperation;
    }
}

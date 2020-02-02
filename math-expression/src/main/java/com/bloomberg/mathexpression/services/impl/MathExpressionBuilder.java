package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.domain.OperationType;
import com.bloomberg.mathexpression.services.MathExpressionCreator;

public class MathExpressionBuilder {

    public static MathExpressionCreator build(OperationType operationType) {
        MathExpressionCreator calcOperation = null;
        if (operationType == null) {
            throw new IllegalArgumentException("Operation type must be informed");
        }
        switch (operationType) {
            case ADDICTION:
                calcOperation = new MathExpressionAddictionCreator();
                break;
            case SUBTRACTION:
                calcOperation = new MathExpressionSubtractionCreator();
                break;
            case MULTIPLICATION:
                calcOperation = new MathExpressionMultiplicationCreator();
                break;
            case DIVISION:
                calcOperation = new MathExpressionDivisionCreator();
                break;
        }
        return calcOperation;
    }
}

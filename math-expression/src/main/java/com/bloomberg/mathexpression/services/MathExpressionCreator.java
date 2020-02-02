package com.bloomberg.mathexpression.services;

import com.bloomberg.mathexpression.domain.OperationType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class MathExpressionCreator {

    protected String EXPRESSION_PATTERN = "%s %s %s = %s => %s %s %s the %s is %s";

    public String create(BigDecimal number1, BigDecimal number2, BigDecimal result, OperationType operationType) {
        number1 = setScale(number1);
        number2 = setScale(number2);
        result = setScale(result);
        return String.format(EXPRESSION_PATTERN, number1, operationType.getOperator(), number2, result,
                number1, getOperationName(), number2, getOperationResultName(), result);
    }

    protected abstract String getOperationName();

    protected abstract String getOperationResultName();

    private BigDecimal setScale(BigDecimal value) {
        return (value != null) ? value.setScale(2, RoundingMode.HALF_UP) : null;
    }
}

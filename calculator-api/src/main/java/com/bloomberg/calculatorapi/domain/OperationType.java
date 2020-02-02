package com.bloomberg.calculatorapi.domain;

import java.util.Arrays;

public enum OperationType {

    ADDICTION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private String operator;

    OperationType(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static OperationType findByOperator(String operator) {
        return Arrays.stream(values()).filter(operation -> operation.getOperator().equals(operator))
                .findFirst().orElse(null);
    }
}

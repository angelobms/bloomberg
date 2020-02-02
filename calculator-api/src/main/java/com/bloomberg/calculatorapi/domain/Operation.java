package com.bloomberg.calculatorapi.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Operation implements DTO {

    private BigDecimal number1;

    private BigDecimal number2;

    private OperationType operation;

    private BigDecimal result;
}

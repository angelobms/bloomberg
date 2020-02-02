package com.bloomberg.mathexpression.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Operation implements DTO {

    private BigDecimal number1;

    private BigDecimal number2;

    private OperationType operation;

}

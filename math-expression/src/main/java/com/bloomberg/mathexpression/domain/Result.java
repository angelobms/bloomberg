package com.bloomberg.mathexpression.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Result implements DTO {

    private BigDecimal number1;

    private BigDecimal number2;

    private String operation;

    private String expression;

    private BigDecimal result;

}

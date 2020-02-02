package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.services.CalcOperation;

import java.math.BigDecimal;

public class CalcOperationMultiplicationStrategy implements CalcOperation {

    @Override
    public BigDecimal calc(BigDecimal multiplicand, BigDecimal multiplier) {
        return setScale(multiplicand.multiply(multiplier));
    }
}

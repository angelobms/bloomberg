package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.services.CalcOperation;

import java.math.BigDecimal;

public class CalcOperationSubtractionStrategy implements CalcOperation {
    @Override
    public BigDecimal calc(BigDecimal minuend, BigDecimal subtrahend) {

        return setScale(minuend.subtract(subtrahend));
    }
}

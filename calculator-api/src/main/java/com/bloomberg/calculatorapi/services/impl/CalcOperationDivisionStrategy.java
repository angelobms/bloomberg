package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.services.CalcOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcOperationDivisionStrategy implements CalcOperation {

    @Override
    public BigDecimal calc(BigDecimal dividend, BigDecimal divisor) {

        return setScale(dividend.divide(divisor, RoundingMode.HALF_UP));
    }
}

package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.services.CalcOperation;

import java.math.BigDecimal;


public class CalcOperationAddictionStrategy implements CalcOperation {

    @Override
    public BigDecimal calc(BigDecimal addend1, BigDecimal addend2) {

        return setScale(addend1.add(addend2));
    }
}

package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.exceptions.DivisionByZeroException;
import com.bloomberg.calculatorapi.exceptions.ErrorCode;
import com.bloomberg.calculatorapi.services.CalcOperation;
import com.bloomberg.calculatorapi.services.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Operation calc(Operation operation) {

        CalcOperation calcOperation = CalcOperationBuilder.build(operation.getOperation());

        try {
            operation.setResult(calcOperation.calc(operation.getNumber1(), operation.getNumber2()));
        } catch (ArithmeticException e) {
            throw new DivisionByZeroException(ErrorCode.DIVISION_BY_ZERO);
        }
        return operation;
    }

}

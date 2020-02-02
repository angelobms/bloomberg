package com.bloomberg.calculatorapi.helpers;

import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;

import java.math.BigDecimal;

public class OperationHelper {

    public static CalculatorRequest makeCalculatorRequest(BigDecimal number1, BigDecimal number2, String operationType) {
        CalculatorRequest request = new CalculatorRequest();
        request.setNumber1(number1);
        request.setNumber2(number2);
        request.setOperation(operationType);
        return request;
    }

    public static Operation makeOperationObject(BigDecimal number1, BigDecimal number2, OperationType operationType) {
        return makeOperationObject(number1, number2, operationType, null);
    }

    public static Operation makeOperationObject(BigDecimal number1, BigDecimal number2,
                                                OperationType operationType, BigDecimal result) {
        Operation operation = new Operation();
        operation.setNumber1(number1);
        operation.setNumber2(number2);
        operation.setOperation(operationType);
        operation.setResult(result);
        return operation;
    }

    public static CalculatorResponse makeCalculatorResponse(BigDecimal number1, BigDecimal number2,
                                                            String operation, BigDecimal result) {
        CalculatorResponse response = new CalculatorResponse();
        response.setNumber1(number1);
        response.setNumber2(number2);
        response.setOperation(operation);
        response.setResult(result);
        return response;
    }


}

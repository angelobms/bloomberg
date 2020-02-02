package com.bloomberg.mathexpression.helpers;

import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.OperationType;
import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.web.requests.CalculatorRequest;
import com.bloomberg.mathexpression.web.responses.CalculatorResponse;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;

import java.math.BigDecimal;

public class MathExpressionHelper {

    public static CalculatorRequest makeCalculatorRequest(BigDecimal number1, BigDecimal number2, String operationType) {
        CalculatorRequest request = new CalculatorRequest();
        request.setNumber1(number1);
        request.setNumber2(number2);
        request.setOperation(operationType);
        return request;
    }

    public static Operation makeOperationObject(BigDecimal number1, BigDecimal number2, OperationType operationType) {
        Operation operation = new Operation();
        operation.setNumber1(number1);
        operation.setNumber2(number2);
        operation.setOperation(operationType);
        return operation;
    }

    public static Result makeResultObject(BigDecimal number1, BigDecimal number2, OperationType operationType) {
        return makeResultObject(number1, number2, operationType, null, null);
    }

    public static Result makeResultObject(BigDecimal number1, BigDecimal number2, OperationType operationType,
                                             String expression, BigDecimal result) {
        Result operation = new Result();
        operation.setNumber1(number1);
        operation.setNumber2(number2);
        operation.setOperation(operationType.name());
        operation.setResult(result);
        operation.setExpression(expression);
        return operation;
    }

    public static CalculatorResponse makeCalculatorResponse(BigDecimal number1, BigDecimal number2,
                                                            String operation,
                                                            String expression, BigDecimal result) {
        CalculatorResponse response = new CalculatorResponse();
        response.setNumber1(number1);
        response.setNumber2(number2);
        response.setOperation(operation);
        response.setResult(result);
        response.setExpression(expression);
        return response;
    }


    public static Application makeApplication(String name) {
        Application application = new Application(name);
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
                .setInstanceId(name)
                .setAppName(name)
                .setIPAddr("localhost")
                .setPort(8080)
                .build();
        application.addInstance(instanceInfo);
        return application;
    }
}

package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.services.MathExpressionCreator;
import com.bloomberg.mathexpression.services.MathExpressionService;
import com.bloomberg.mathexpression.services.external.CalculatorServiceClient;
import com.bloomberg.mathexpression.services.external.requests.CalculatorServiceProxyRequest;
import com.bloomberg.mathexpression.web.controllers.MathExpressionControllerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathExpressionServiceImpl implements MathExpressionService {

    private static Logger LOGGER = LoggerFactory.getLogger(MathExpressionControllerImpl.class);

    private CalculatorServiceClient calculatorServiceClient;

    @Autowired
    public MathExpressionServiceImpl(CalculatorServiceClient calculatorServiceClient) {
        this.calculatorServiceClient = calculatorServiceClient;
    }

    @Override
    public Result calc(Operation operation) {

        CalculatorServiceProxyRequest request = new CalculatorServiceProxyRequest(operation.getNumber1().doubleValue(),
                operation.getNumber2().doubleValue(), operation.getOperation().getOperator());

        Result result = calculatorServiceClient.calc(request);

        LOGGER.info("Creating expression with result of Calculator API...");
        MathExpressionCreator expressionCreator = MathExpressionBuilder.build(operation.getOperation());
        String expression = expressionCreator.create(operation.getNumber1(),
                operation.getNumber2(), result.getResult(), operation.getOperation());
        result.setExpression(expression);
        LOGGER.info("Expression: {}", expression);

        return result;
    }

}

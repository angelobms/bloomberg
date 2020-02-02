package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.exceptions.DivisionByZeroException;
import com.bloomberg.calculatorapi.exceptions.ErrorCode;
import com.bloomberg.calculatorapi.services.CalculatorService;
import org.junit.jupiter.api.Test;

import static com.bloomberg.calculatorapi.helpers.OperationHelper.makeOperationObject;
import static com.bloomberg.calculatorapi.utils.NumberUtils.toBigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceImplTest {

    private CalculatorService calculatorService = new CalculatorServiceImpl();

    @Test
    void shouldPerformAnAddiction() {

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.ADDICTION);
        Operation expected = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.ADDICTION, toBigDecimal(6.3));

        Operation actual = calculatorService.calc(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }

    @Test
    void shouldPerformAnSubtraction() {

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.SUBTRACTION);
        Operation expected = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.SUBTRACTION, toBigDecimal(-1.7));

        Operation actual = calculatorService.calc(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }

    @Test
    void shouldPerformAnMultiplication() {

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.MULTIPLICATION);
        Operation expected = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.MULTIPLICATION, toBigDecimal(9.2));

        Operation actual = calculatorService.calc(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }

    @Test
    void shouldPerformAnDivision() {

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.DIVISION);
        Operation expected = makeOperationObject(toBigDecimal(2.3), toBigDecimal(4d),
                OperationType.DIVISION, toBigDecimal(0.575));

        Operation actual = calculatorService.calc(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }

    @Test
    void shouldThrownDivisionByZeroExceptionWhenTryingPerformADivisionUsingZeroAsDivisor() {

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(0d),
                OperationType.DIVISION);
        String expected = ErrorCode.DIVISION_BY_ZERO;

        Exception actual = assertThrows(DivisionByZeroException.class, () -> {
           calculatorService.calc(input);
        });

        assertEquals(expected, actual.getMessage());
    }
}
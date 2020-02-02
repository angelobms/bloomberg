package com.bloomberg.calculatorapi.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OperationTypeTest {

    @Test
    void shouldFindOperationTypeByAddictionOperator() {
        String input = "+";
        OperationType expected = OperationType.ADDICTION;

        OperationType actual = OperationType.findByOperator(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindOperationTypeBySubtractionOperator() {
        String input = "-";
        OperationType expected = OperationType.SUBTRACTION;

        OperationType actual = OperationType.findByOperator(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindOperationTypeByMultiplicationOperator() {
        String input = "*";
        OperationType expected = OperationType.MULTIPLICATION;

        OperationType actual = OperationType.findByOperator(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindOperationTypeByDivisionOperator() {
        String input = "/";
        OperationType expected = OperationType.DIVISION;

        OperationType actual = OperationType.findByOperator(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNullWhenTryingFindOperationTypeUsingInvalidOperator() {
        String input = "invalid";

        OperationType actual = OperationType.findByOperator(input);

        assertNull(actual);
    }
}
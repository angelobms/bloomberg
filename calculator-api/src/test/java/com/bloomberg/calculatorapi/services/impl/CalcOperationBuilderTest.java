package com.bloomberg.calculatorapi.services.impl;

import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.services.CalcOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcOperationBuilderTest {

    @Test
    void shouldReturnAddictionStrategyWithGivenAddictionOperationType() {

        OperationType input = OperationType.ADDICTION;
        CalcOperation expected = new CalcOperationAddictionStrategy();
        CalcOperation unexpected = new CalcOperationDivisionStrategy();

        CalcOperation actual = CalcOperationBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnSubtractionStrategyWithGivenSubtractionOperationType() {

        OperationType input = OperationType.SUBTRACTION;
        CalcOperation expected = new CalcOperationSubtractionStrategy();
        CalcOperation unexpected = new CalcOperationDivisionStrategy();

        CalcOperation actual = CalcOperationBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnMultiplicationStrategyWithGivenMultiplicationOperationType() {

        OperationType input = OperationType.MULTIPLICATION;
        CalcOperation expected = new CalcOperationMultiplicationStrategy();
        CalcOperation unexpected = new CalcOperationDivisionStrategy();

        CalcOperation actual = CalcOperationBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnDivisionStrategyWithGivenDivisionOperationType() {

        OperationType input = OperationType.DIVISION;
        CalcOperation expected = new CalcOperationDivisionStrategy();
        CalcOperation unexpected = new CalcOperationSubtractionStrategy();

        CalcOperation actual = CalcOperationBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldThrownIllegalArgumentExceptionWhenTryingToBuildWithNullOperationType() {
        OperationType input = null;

        String expectedMessage = "Operation type must be informed";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalcOperationBuilder.build(input);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
}
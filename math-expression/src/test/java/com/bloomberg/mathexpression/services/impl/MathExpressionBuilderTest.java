package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.domain.OperationType;
import com.bloomberg.mathexpression.services.MathExpressionCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathExpressionBuilderTest {

    @Test
    void shouldReturnAddictionCreatorWithGivenAddictionOperationType() {

        OperationType input = OperationType.ADDICTION;
        MathExpressionCreator expected = new MathExpressionAddictionCreator();
        MathExpressionCreator unexpected = new MathExpressionDivisionCreator();

        MathExpressionCreator actual = MathExpressionBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnAddictionCreatorWithGivenSubtractionOperationType() {

        OperationType input = OperationType.SUBTRACTION;
        MathExpressionCreator expected = new MathExpressionSubtractionCreator();
        MathExpressionCreator unexpected = new MathExpressionDivisionCreator();

        MathExpressionCreator actual = MathExpressionBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnAddictionCreatorWithGivenMultiplicationOperationType() {

        OperationType input = OperationType.MULTIPLICATION;
        MathExpressionCreator expected = new MathExpressionMultiplicationCreator();
        MathExpressionCreator unexpected = new MathExpressionDivisionCreator();

        MathExpressionCreator actual = MathExpressionBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldReturnAddictionCreatorWithGivenDivisionOperationType() {

        OperationType input = OperationType.DIVISION;
        MathExpressionCreator expected = new MathExpressionDivisionCreator();
        MathExpressionCreator unexpected = new MathExpressionAddictionCreator();

        MathExpressionCreator actual = MathExpressionBuilder.build(input);

        assertEquals(expected.getClass(), actual.getClass());
        assertNotEquals(unexpected.getClass(), actual.getClass());
    }

    @Test
    void shouldThrownIllegalArgumentExceptionWhenTryingToBuildWithNullOperationType() {
        OperationType input = null;

        String expectedMessage = "Operation type must be informed";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MathExpressionBuilder.build(input);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
}
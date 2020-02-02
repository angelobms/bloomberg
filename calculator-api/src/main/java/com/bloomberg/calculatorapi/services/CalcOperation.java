package com.bloomberg.calculatorapi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface CalcOperation {

    /**
     * Perform the operation using the given numbers.
     *
     * @param number1 Number 1.
     * @param number2 Number 2.
     *
     * @return The result of the operation.
     */
    BigDecimal calc(BigDecimal number1, BigDecimal number2);

    /**
     * Defines the default scale to BigDecimal values.
     * @param value BigDecimal value.
     * @return BigDecimal formatted.
     */
    default BigDecimal setScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}

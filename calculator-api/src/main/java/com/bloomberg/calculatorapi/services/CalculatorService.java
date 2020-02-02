package com.bloomberg.calculatorapi.services;

import com.bloomberg.calculatorapi.domain.Operation;

public interface CalculatorService {

    /**
     * Perform the operation based in the given operation type.
     * @param operation Object which contains the numbers and the operation that will be performed with them.
     *
     * @return The result of the operation.
     */
    Operation calc(Operation operation);
}

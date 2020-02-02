package com.bloomberg.calculatorapi.mappers;

import com.bloomberg.calculatorapi.config.CalculatorConfig;
import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bloomberg.calculatorapi.helpers.OperationHelper.*;
import static com.bloomberg.calculatorapi.utils.NumberUtils.toBigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationMapperTest {

    private CalculatorConfig config = new CalculatorConfig();

    private OperationMapper operationMapper;

    @BeforeEach
    void setUp() {
        this.operationMapper = new OperationMapper(config.modelMapper());
    }

    @Test
    void shouldConvertFromRequestToDto() {
        CalculatorRequest input = makeCalculatorRequest(
                toBigDecimal(2d), toBigDecimal(4d),
                "addiction");
        Operation expected = makeOperationObject(
                toBigDecimal(2d), toBigDecimal(4d),
                OperationType.ADDICTION);

        Operation actual = operationMapper.toDto(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
    }

    @Test
    void shouldConvertFromDtoToResponse() {
        Operation input = makeOperationObject(
                toBigDecimal(2d), toBigDecimal(4d),
                OperationType.ADDICTION, toBigDecimal(6d));
        CalculatorResponse expected = makeCalculatorResponse(toBigDecimal(2d), toBigDecimal(4d),
                "ADDICTION", toBigDecimal(6d));

        CalculatorResponse actual = operationMapper.toResponse(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }
}
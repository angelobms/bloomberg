package com.bloomberg.mathexpression.mappers;

import com.bloomberg.mathexpression.config.MathExpressionConfig;
import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.OperationType;
import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.helpers.MathExpressionHelper;
import com.bloomberg.mathexpression.web.requests.CalculatorRequest;
import com.bloomberg.mathexpression.web.responses.CalculatorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bloomberg.mathexpression.helpers.MathExpressionHelper.*;
import static com.bloomberg.mathexpression.utils.NumberUtils.toBigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OperationMapperTest {

    private MathExpressionConfig config = new MathExpressionConfig();

    private OperationMapper operationMapper;

    @BeforeEach
    void setUp() {
        this.operationMapper = new OperationMapper(config.modelMapper());
    }

    @Test
    void shouldConvertFromRequestToDto() {
        CalculatorRequest input = MathExpressionHelper.makeCalculatorRequest(
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
        Result input = makeResultObject(
                toBigDecimal(2d), toBigDecimal(4d),
                OperationType.ADDICTION, "2.00 + 4.00 = 6.00 => 2.00 plus 4.00 the sum is 6.00",
                toBigDecimal(6d));

        CalculatorResponse expected = makeCalculatorResponse(toBigDecimal(2d), toBigDecimal(4d),
                "ADDICTION", "2.00 + 4.00 = 6.00 => 2.00 plus 4.00 the sum is 6.00",
                toBigDecimal(6d));

        CalculatorResponse actual = operationMapper.toResponse(input);

        assertEquals(expected.getNumber1(), actual.getNumber1());
        assertEquals(expected.getNumber2(), actual.getNumber2());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getResult(), actual.getResult());
    }
}
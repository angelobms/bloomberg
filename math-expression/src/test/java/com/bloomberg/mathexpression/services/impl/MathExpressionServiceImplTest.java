package com.bloomberg.mathexpression.services.impl;

import com.bloomberg.mathexpression.domain.Operation;
import com.bloomberg.mathexpression.domain.OperationType;
import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.exceptions.ErrorCode;
import com.bloomberg.mathexpression.exceptions.ServiceUnavailableException;
import com.bloomberg.mathexpression.helpers.MathExpressionHelper;
import com.bloomberg.mathexpression.services.external.CalculatorServiceClient;
import com.bloomberg.mathexpression.utils.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bloomberg.mathexpression.helpers.MathExpressionHelper.makeOperationObject;
import static com.bloomberg.mathexpression.helpers.MathExpressionHelper.makeResultObject;
import static com.bloomberg.mathexpression.utils.NumberUtils.toBigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MathExpressionServiceImplTest {

    @Mock
    private CalculatorServiceClient calculatorServiceClient;

    @InjectMocks
    private MathExpressionServiceImpl mathExpressionService;

    @BeforeEach
    void setUp() {
        Result defaultExpectedValue = makeResultObject(
                toBigDecimal(2.2), toBigDecimal(4d),OperationType.ADDICTION,
                 "2.20 + 4.00 = 6.20 => 2.20 plus 4.00 the sum is 6.20", toBigDecimal(6.2));
        Mockito.lenient().when(calculatorServiceClient.calc(any()))
                .thenReturn(defaultExpectedValue);
    }

    @Test
    void shouldPerformAnOperation() {
        Operation input = makeOperationObject(toBigDecimal(2.2), toBigDecimal(4d), OperationType.ADDICTION);
        Result expected = makeResultObject(toBigDecimal(2.2), toBigDecimal(4d), OperationType.ADDICTION,
                "2.20 + 4.00 = 6.20 => 2.20 plus 4.00 the sum is 6.20", toBigDecimal(6.2));

        Result actual = mathExpressionService.calc(input);

        assertAll("result", () -> assertEquals(expected.getNumber1(), actual.getNumber1()),
                () -> assertEquals(expected.getNumber2(), actual.getNumber2()),
                () -> assertEquals(expected.getOperation(), actual.getOperation()),
                () -> assertEquals(expected.getExpression(), actual.getExpression()),
                () -> assertEquals(expected.getResult(), actual.getResult())
        );
    }

    @Test
    void shouldThrownServiceUnavailableWhenCalculatorApiOffline() {

        Mockito.when(calculatorServiceClient.calc(any()))
                .thenThrow(new ServiceUnavailableException(new RuntimeException()));

        Operation input = makeOperationObject(toBigDecimal(2.3), toBigDecimal(0d),
                OperationType.DIVISION);

        String expected = ErrorCode.SERVICE_UNAVAILABLE;

        Exception actual = assertThrows(ServiceUnavailableException.class, () -> {
            mathExpressionService.calc(input);
        });

        assertEquals(expected, actual.getMessage());
    }
}
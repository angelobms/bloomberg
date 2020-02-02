package com.bloomberg.calculatorapi.web.requests;

import com.bloomberg.calculatorapi.annotations.EnumValidator;
import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonRootName("calculator")
@Data
public class CalculatorRequest implements Request {

    @NotNull(message = ErrorCode.NOT_NULL)
    private BigDecimal number1;

    @NotNull(message = ErrorCode.NOT_NULL)
    private BigDecimal number2;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    @EnumValidator(enumClass = OperationType.class, message = ErrorCode.ENUM_OPERATION_TYPE, required = false)
    private String operation;

}

package com.bloomberg.calculatorapi.web.requests;

import com.bloomberg.calculatorapi.annotations.EnumValidator;
import com.bloomberg.calculatorapi.domain.OperationType;
import com.bloomberg.calculatorapi.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonRootName("calculator")
@Data
public class CalculatorRequest implements Request {

    @Schema(description = "Number1", example = "1", required = true)
    @NotNull(message = ErrorCode.NOT_NULL)
    private BigDecimal number1;

    @Schema(description = "Number2", example = "1", required = true)
    @NotNull(message = ErrorCode.NOT_NULL)
    private BigDecimal number2;

    @Schema(description = "Operator", example = "+", required = true)
    @NotBlank(message = ErrorCode.NOT_BLANK)
    @EnumValidator(enumClass = OperationType.class, message = ErrorCode.ENUM_OPERATION_TYPE, required = false)
    private String operation;

}

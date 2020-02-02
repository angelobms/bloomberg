package com.bloomberg.calculatorapi.web.responses;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.math.BigDecimal;

@JsonRootName("calculator_response")
@Data
public class CalculatorResponse implements Response {

    private BigDecimal number1;

    private BigDecimal number2;

    private String operation;

    private BigDecimal result;

}

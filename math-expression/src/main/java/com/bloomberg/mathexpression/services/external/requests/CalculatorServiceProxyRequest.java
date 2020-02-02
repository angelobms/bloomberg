package com.bloomberg.mathexpression.services.external.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculatorServiceProxyRequest {

    private Double number1;

    private Double number2;

    private String operation;

}

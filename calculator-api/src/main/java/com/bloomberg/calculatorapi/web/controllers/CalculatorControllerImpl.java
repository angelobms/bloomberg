package com.bloomberg.calculatorapi.web.controllers;

import com.bloomberg.calculatorapi.domain.Operation;
import com.bloomberg.calculatorapi.mappers.OperationMapper;
import com.bloomberg.calculatorapi.services.CalculatorService;
import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/calculators")
public class CalculatorControllerImpl implements CalculatorController {

    private CalculatorService calculatorService;

    private OperationMapper mapper;

    @Autowired
    public CalculatorControllerImpl(CalculatorService calculatorService, OperationMapper mapper) {
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<CalculatorResponse> calc(@Valid CalculatorRequest request) {

        Operation operation = mapper.toDto(request);

        Operation calculatedOperation = calculatorService.calc(operation);

        return ResponseEntity.ok(mapper.toResponse(calculatedOperation));
    }
}

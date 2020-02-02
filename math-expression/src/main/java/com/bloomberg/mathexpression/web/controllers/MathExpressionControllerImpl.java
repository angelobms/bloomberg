package com.bloomberg.mathexpression.web.controllers;

import com.bloomberg.mathexpression.domain.Result;
import com.bloomberg.mathexpression.mappers.OperationMapper;
import com.bloomberg.mathexpression.services.MathExpressionService;
import com.bloomberg.mathexpression.web.requests.CalculatorRequest;
import com.bloomberg.mathexpression.web.responses.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MathExpressionControllerImpl {

    private static Logger LOGGER = LoggerFactory.getLogger(MathExpressionControllerImpl.class);

    private MathExpressionService mathExpressionService;

    private OperationMapper operationMapper;

    @Autowired
    public MathExpressionControllerImpl(MathExpressionService mathExpressionService, OperationMapper operationMapper) {
        this.mathExpressionService = mathExpressionService;
        this.operationMapper = operationMapper;
    }

    @PutMapping(value = "/expressions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculatorResponse> calc(@Valid @RequestBody CalculatorRequest request) {

        LOGGER.info("Beginning of calculation");

        Result result = mathExpressionService.calc(operationMapper.toDto(request));

        CalculatorResponse response = operationMapper.toResponse(result);

        LOGGER.info("Ending of calculation");

        return ResponseEntity.ok(response);
    }
}

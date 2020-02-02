package com.bloomberg.calculatorapi.web.controllers;

import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CalculatorController {

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CalculatorResponse> calc(@Valid @RequestBody CalculatorRequest request);
}

package com.bloomberg.calculatorapi.web.controllers;

import com.bloomberg.calculatorapi.web.requests.CalculatorRequest;
import com.bloomberg.calculatorapi.web.responses.CalculatorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CalculatorController {

    @Operation(
            summary = "Perform a math operation",
            description = "Perform a math operation with the given values and operator",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = CalculatorRequest.class))),
            responses = {
                @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = CalculatorResponse.class))),
                @ApiResponse(responseCode = "400", description = "Invalid input",
                        content = @Content(mediaType = "application/json"))
            }
    )
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CalculatorResponse> calc(
            @Parameter(description="Calculator request cannot be empty.", required=true)
            @Valid @RequestBody CalculatorRequest request);
}

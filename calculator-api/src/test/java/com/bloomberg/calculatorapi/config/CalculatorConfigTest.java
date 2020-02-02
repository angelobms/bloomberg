package com.bloomberg.calculatorapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorConfigTest {

    private CalculatorConfig config = new CalculatorConfig();

    @Test
    void shouldCreateAnObjectMapper() {

        ObjectMapper objectMapper = config.objectMapper();

        assertNotNull(objectMapper);
    }

    @Test
    void shouldCreateAModelMapper() {

        ModelMapper modelMapper = config.modelMapper();

        assertNotNull(modelMapper);
    }
}
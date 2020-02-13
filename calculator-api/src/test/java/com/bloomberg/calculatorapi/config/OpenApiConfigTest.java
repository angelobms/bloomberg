package com.bloomberg.calculatorapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenApiConfigTest {

    private OpenApiConfig config = new OpenApiConfig();

    @Test
    void shouldCreateAnCustomOpenAPI() {

        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI);
        assertEquals("Calculator API", openAPI.getInfo().getTitle());
    }
}
package com.bloomberg.mathexpression.web.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public abstract class BaseIntegrationTest {

    @Autowired
    Environment environment;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        Assumptions.assumeTrue(Arrays.asList(this.environment.getActiveProfiles()).contains("integration"));
        RestAssured.baseURI = "http://localhost/api/v1";
        RestAssured.port = port;
    }
}

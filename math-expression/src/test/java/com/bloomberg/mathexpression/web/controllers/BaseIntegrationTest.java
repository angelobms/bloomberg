package com.bloomberg.mathexpression.web.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class BaseIntegrationTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost/api/v1";
        RestAssured.port = port;
    }
}

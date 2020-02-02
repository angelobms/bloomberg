package com.bloomberg.mathexpression.web.controllers;

import com.bloomberg.mathexpression.web.requests.CalculatorRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MathExpressionIntegrationTest extends BaseIntegrationTest {

    @Autowired
    Environment environment;

    @BeforeEach
    void setup() {
        Assumptions.assumeTrue(Arrays.asList(this.environment.getActiveProfiles()).contains("integration"));
    }

    @Test
    public void whenPutRequestToCalculators_Then_CorrectResponse() {

        CalculatorRequest request = new CalculatorRequest();
        request.setNumber1(new BigDecimal("2.2"));
        request.setNumber2(new BigDecimal("4"));
        request.setOperation("+");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .log().ifValidationFails()
                .put("/expressions")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void givenInvalidOperatorWhenPutRequestToCalculators_Then_BadRequest() {

        CalculatorRequest request = new CalculatorRequest();
        request.setNumber1(new BigDecimal("2.2"));
        request.setNumber2(new BigDecimal("4"));
        request.setOperation("A");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .log().ifValidationFails()
                .put("/expressions")
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void givenZeroAsDivisorWhenPutRequestToCalculators_Then_BadRequest() {

        CalculatorRequest request = new CalculatorRequest();
        request.setNumber1(new BigDecimal("2.2"));
        request.setNumber2(new BigDecimal("0"));
        request.setOperation("/");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .log().ifValidationFails()
                .put("/expressions")
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }
}
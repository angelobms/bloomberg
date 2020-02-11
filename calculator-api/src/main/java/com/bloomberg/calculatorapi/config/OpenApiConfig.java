package com.bloomberg.calculatorapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Calculator API")
                        .description("API for basic math operations.")
                        .contact(new Contact()
                                .name("Vítor Franco do Carmo")
                                .email("vfcarmo@gmail.com")
                                .url("https://www.linkedin.com/in/v%C3%ADtor-franco-do-carmo-714a2340/")));
    }
}

package com.codecon.hackaton.hackanjos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Useless Ranked API")
                        .version("1.0")
                        .description("API inútil de um rank inútil - Codecon Universe"));
    }
}

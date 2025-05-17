package com.codecon.hackaton.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hackaton API")
                        .version("1.0")
                        .description("API para o projeto Hackaton")
                        .contact(new Contact()
                                .name("Hackaton Team")
                                .email("contato@hackaton.com")));
    }
} 
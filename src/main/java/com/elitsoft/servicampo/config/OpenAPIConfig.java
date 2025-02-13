package com.elitsoft.servicampo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class OpenAPIConfig {

    /**
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shopping Cart API") // Your API title
                        .description("API for managing shopping carts") // Your API description
                        .version("1.0")); // Your API version
    }
}
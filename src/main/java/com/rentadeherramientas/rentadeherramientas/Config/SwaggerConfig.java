package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig<Docket> {

    private static final String RequestHandlerSelectors = null;
    private static final String DocumentationType = null;
    private static final String PathSelectors = null;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.rentadeherramientas.rentadeherramientas"))
            .paths(PathSelectors.any())
            .build();
    }
}


package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@CrossOrigin(
    origins = {
        "http://localhost:4200",
        "http://localhost",
        "http://127.0.0.1"
    },
    allowCredentials = "true"
)
public class CorsConfig implements WebMvcConfigurer {
}
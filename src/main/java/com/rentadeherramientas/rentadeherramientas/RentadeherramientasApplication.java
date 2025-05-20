package com.rentadeherramientas.rentadeherramientas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class RentadeherramientasApplication {

<<<<<<< HEAD
    private static final Logger logger = LoggerFactory.getLogger(RentadeherramientasApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RentadeherramientasApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        logger.info("Iniciando aplicación de Renta de Herramientas...");
        app.run(args);
        logger.info("Aplicación iniciada exitosamente!");
    }
}
=======
    public static void main(String[] args) {
        SpringApplication.run(RentadeherramientasApplication.class, args);
    }

}
>>>>>>> feature/add-provedores

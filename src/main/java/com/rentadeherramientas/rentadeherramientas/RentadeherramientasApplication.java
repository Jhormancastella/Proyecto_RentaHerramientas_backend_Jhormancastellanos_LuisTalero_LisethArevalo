package com.rentadeherramientas.rentadeherramientas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RentadeherramientasApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(RentadeherramientasApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RentadeherramientasApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        logger.info("╔════════════════════════════════════════╗");
        logger.info("║    Renta de Herramientas - Backend     ║");
        logger.info("║    Servidor iniciado correctamente     ║");
        logger.info("╠════════════════════════════════════════╣");
        logger.info("║    Credenciales de Administrador:      ║");
        logger.info("║    Usuario: admin                      ║");
        logger.info("║    Contraseña: admin123                ║");
        logger.info("╚════════════════════════════════════════╝");
    }
}

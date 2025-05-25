package com.rentadeherramientas.rentadeherramientas.application.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class paymentService {

    private static final Logger logger = LoggerFactory.getLogger(paymentService.class);

    public boolean processPayment(String userId, double amount) {
        logger.info("Procesando pago para el usuario: {} por un monto de: {}", userId, amount);

        if (amount <= 0) {
            logger.warn("El monto del pago debe ser mayor que cero.");
            return false;
        }

        try {
            // Simulación de integración con proveedor de pagos
            boolean paymentSuccess = simulatePaymentProvider(userId, amount);
            if (paymentSuccess) {
                logger.info("Pago procesado exitosamente para el usuario: {}", userId);
                return true;
            } else {
                logger.error("Error al procesar el pago para el usuario: {}", userId);
                return false;
            }
        } catch (Exception e) {
            logger.error("Excepción al procesar el pago: {}", e.getMessage());
            return false;
        }
    }

    private boolean simulatePaymentProvider(String userId, double amount) {
        // Aquí podrías simular fallos aleatorios o lógicas adicionales
        return true;
    }
}
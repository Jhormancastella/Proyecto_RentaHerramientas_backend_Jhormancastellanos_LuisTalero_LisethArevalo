package com.rentadeherramientas.rentadeherramientas.application.services;

import org.springframework.stereotype.Service;



@Service
public class paymentService {

    public boolean processPayment(String userId, double amount) {
        // Lógica simulada para procesar el pago
        if (amount <= 0) {
            return false;
        }
        // Aquí iría la integración con un proveedor de pagos real
        return true;
    }
}
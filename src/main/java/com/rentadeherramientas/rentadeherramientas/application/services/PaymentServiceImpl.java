package com.rentadeherramientas.rentadeherramientas.application.services;

import org.springframework.stereotype.Service;

import com.payment.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

    public boolean processPayment(double amount, String paymentMethod) {

        if (amount > 0 && paymentMethod != null && !paymentMethod.isEmpty()) {

            System.out.println("Procesando pago de $" + amount + " usando " + paymentMethod);

            return true;
        }
        return false;
    }
}
package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Payment;
import com.rentadeherramientas.rentadeherramientas.domain.entity.PaymentStatus;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class paymentService {

    private static final Logger logger = LoggerFactory.getLogger(paymentService.class);
    private final PaymentRepository paymentRepository;

    public paymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Optional<Payment> existing = paymentRepository.findById(id);
        if (existing.isEmpty()) return null;

        updatedPayment.setId(id);
        updatedPayment.setCreatedAt(existing.get().getCreatedAt());
        updatedPayment.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(updatedPayment);
    }

    public boolean deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) return false;
        paymentRepository.deleteById(id);
        return true;
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByReservation_User_Id(userId);
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    public List<Payment> getPaymentsWithAmountGreaterThan(BigDecimal amount) {
        return paymentRepository.findPaymentsWithAmountGreaterThan(amount);
    }

    public boolean processPayment(String userId, double amount) {
        logger.info("Procesando pago para el usuario: {} por un monto de: {}", userId, amount);

        if (amount <= 0) {
            logger.warn("El monto del pago debe ser mayor que cero.");
            return false;
        }

        try {
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
        return true;
    }
}

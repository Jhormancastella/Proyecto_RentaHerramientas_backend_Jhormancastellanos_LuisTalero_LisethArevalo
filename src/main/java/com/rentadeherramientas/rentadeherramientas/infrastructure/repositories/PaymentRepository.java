package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Payment;
import com.rentadeherramientas.rentadeherramientas.domain.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Buscar pagos por ID de usuario a través de la reserva
    List<Payment> findByReservation_User_Id(Long userId);

    // Buscar pagos por estado (usando el enum)
    List<Payment> findByStatus(PaymentStatus status);

    // Consulta personalizada con BigDecimal
    @Query("SELECT p FROM Payment p WHERE p.amount > ?1")
    List<Payment> findPaymentsWithAmountGreaterThan(BigDecimal amount);
}
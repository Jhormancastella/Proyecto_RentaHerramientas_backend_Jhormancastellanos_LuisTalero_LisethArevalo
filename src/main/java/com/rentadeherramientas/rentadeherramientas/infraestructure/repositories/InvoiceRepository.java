package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByReservation_UserId(Long userId);
    List<Invoice> findByIssueDateBetween(LocalDateTime start, LocalDateTime end);
    Optional<Invoice> findByReservationId(Long reservationId);
}
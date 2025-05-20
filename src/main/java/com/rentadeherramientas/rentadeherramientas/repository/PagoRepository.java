package com.rentadeherramientas.rentadeherramientas.repository;

import com.rentaherramientas.model.Pago;
import com.rentaherramientas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByReserva(Reserva reserva);
}

package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClienteId(Long clienteId);
    List<Reservation> findByToolId(Long toolId);
}
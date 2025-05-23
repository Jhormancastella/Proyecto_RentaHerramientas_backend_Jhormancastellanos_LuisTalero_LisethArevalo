package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ReservationRepository extends JpaRepository<com.rentadeherramientas.rentadeherramientas.domain.entity.Reservation, Long> {

}
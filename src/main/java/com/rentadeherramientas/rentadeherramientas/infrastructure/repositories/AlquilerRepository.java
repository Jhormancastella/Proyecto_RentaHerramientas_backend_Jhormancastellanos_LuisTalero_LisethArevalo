package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByEstado(String estado);
}
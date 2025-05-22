package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Alquiler;

@Repository
public interface alquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByEstado(String estado);

    List<Alquiler> findByFechaInicioBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Alquiler> findByTool(Tool tool);

    List<Alquiler> findByToolAndEstado(Tool tool, String estado);

    Alquiler save(Alquiler alquiler);

    void deleteById(Long id);
}

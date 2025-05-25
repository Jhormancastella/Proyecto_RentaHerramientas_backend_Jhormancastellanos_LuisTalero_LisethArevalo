package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.DamageReport;

import java.util.List;

@Repository
public interface DamageReportRepository extends JpaRepository<DamageReport, Long> {
    // Buscar reportes por estado
    List<DamageReport> findByStatus(String status);

    // Buscar reportes por id de herramienta
    List<DamageReport> findByToolId(Long toolId);

    // Buscar reportes por usuario que reportó
    List<DamageReport> findByReportedBy(String reportedBy);
}

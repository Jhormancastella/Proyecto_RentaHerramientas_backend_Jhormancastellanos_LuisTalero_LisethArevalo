package com.rentadeherramientas.rentadeherramientas.infrastructure.repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.DamageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DamageReportRepository extends JpaRepository<DamageReport, Long> {
    List<DamageReport> findByToolId(Long toolId);
    List<DamageReport> findByReservationId(Long reservationId);
}
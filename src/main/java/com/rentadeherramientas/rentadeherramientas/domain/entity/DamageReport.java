package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Table(name = "damage_reports")
public class DamageReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si tu DB usa autoincremento
    private Long id;
    private Long toolId;
    private String description;
    private LocalDate reportDate;
    private String reportedBy;
    private String status; // Nuevo campo

    public DamageReport() {
    }

    public DamageReport(Long id, Long toolId, String description, LocalDate reportDate, String reportedBy, String status) {
        this.id = id;
        this.toolId = toolId;
        this.description = description;
        this.reportDate = reportDate;
        this.reportedBy = reportedBy;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    // Métodos completados
    public LocalDate getDateReported() {
        return reportDate;
    }

    public void setDateReported(LocalDate dateReported) {
        this.reportDate = dateReported;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

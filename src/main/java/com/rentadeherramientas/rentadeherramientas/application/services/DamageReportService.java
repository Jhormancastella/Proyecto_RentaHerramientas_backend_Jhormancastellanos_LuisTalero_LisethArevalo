package com.rentadeherramientas.rentadeherramientas.application.services;


import com.rentadeherramientas.rentadeherramientas.domain.entity.DamageReport;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.DamageReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DamageReportService {

    private final DamageReportRepository damageReportRepository;

    @Autowired
    public DamageReportService(DamageReportRepository damageReportRepository) {
        this.damageReportRepository = damageReportRepository;
    }

    public List<DamageReport> getAllDamageReports() {
        return damageReportRepository.findAll();
    }

    public Optional<DamageReport> getDamageReportById(Long id) {
        return damageReportRepository.findById(id);
    }

    public DamageReport createDamageReport(DamageReport damageReport) {
        return damageReportRepository.save(damageReport);
    }

    public Optional<DamageReport> updateDamageReport(Long id, DamageReport damageReportDetails) {
        return damageReportRepository.findById(id).map(damageReport -> {
            damageReport.setDescription(damageReportDetails.getDescription());
            damageReport.setDateReported(damageReportDetails.getDateReported());
            damageReport.setToolId(damageReportDetails.getToolId());
            damageReport.setStatus(damageReportDetails.getStatus());
            return damageReportRepository.save(damageReport);
        });
    } 

    public boolean deleteDamageReport(Long id) {
        return damageReportRepository.findById(id).map(damageReport -> {
            damageReportRepository.delete(damageReport);
            return true;
        }).orElse(false);
    }
}
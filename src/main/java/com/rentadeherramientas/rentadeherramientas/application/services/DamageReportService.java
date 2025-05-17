package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.DamageReport;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.DamageReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DamageReportService {
    private final DamageReportRepository damageReportRepository;

    @Autowired
    public DamageReportService(DamageReportRepository damageReportRepository) {
        this.damageReportRepository = damageReportRepository;
    }

    @Transactional
    public DamageReport createDamageReport(DamageReport damageReport) {
        return damageReportRepository.save(damageReport);
    }

    @Transactional(readOnly = true)
    public List<DamageReport> getAllDamageReports() {
        return damageReportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DamageReport> getDamageReportById(Long id) {
        return damageReportRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<DamageReport> getDamageReportsByToolId(Long toolId) {
        return damageReportRepository.findByToolId(toolId);
    }

    @Transactional
    public DamageReport updateDamageReport(DamageReport damageReport) {
        return damageReportRepository.save(damageReport);
    }

    @Transactional
    public void deleteDamageReport(Long id) {
        damageReportRepository.deleteById(id);
    }
}

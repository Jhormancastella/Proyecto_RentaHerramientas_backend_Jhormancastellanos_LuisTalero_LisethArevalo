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

    @Transactional
    public DamageReport update(Long id, DamageReport damageReport) {
        Optional<DamageReport> existing = damageReportRepository.findById(id);
        if (existing.isPresent()) {
            DamageReport reportToUpdate = existing.get();
            reportToUpdate.setDescription(damageReport.getDescription());
            reportToUpdate.setTool(damageReport.getTool());
            reportToUpdate.setDateReported(damageReport.getDateReported());
            // Add other fields as needed
            return damageReportRepository.save(reportToUpdate);
        } else {
            throw new IllegalArgumentException("DamageReport not found with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public DamageReport getById(Long id) {
        return damageReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DamageReport not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<DamageReport> getAll() {
        return damageReportRepository.findAll();
    }

    @Transactional
    public DamageReport save(DamageReport damageReport) {
        return damageReportRepository.save(damageReport);
    }

    @Transactional
    public boolean delete(Long id) {
        if (damageReportRepository.existsById(id)) {
            damageReportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentadeherramientas.rentadeherramientas.application.services.DamageReportService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.DamageReport;
import java.util.List;


@RestController
@RequestMapping("/api/damage-reports")
public class DamageReportController {

    @Autowired
    private DamageReportService damageReportService;

    @GetMapping
    public List<DamageReport> getAll() {
        return damageReportService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DamageReport> getById(@PathVariable Long id) {
        return damageReportService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DamageReport create(@RequestBody DamageReport damageReport) {
        return damageReportService.save(damageReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DamageReport> update(@PathVariable Long id, @RequestBody DamageReport damageReport) {
        return damageReportService.update(id, damageReport)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (damageReportService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

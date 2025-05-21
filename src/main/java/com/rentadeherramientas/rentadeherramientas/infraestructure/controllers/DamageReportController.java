package com.rentadeherramientas.rentadeherramientas.infraestructure.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/damage-reports")
public class DamageReportController {

    // Ejemplo de endpoint para obtener todos los reportes de daño
    @GetMapping
    public ResponseEntity<List<String>> getAllDamageReports() {
        // Lógica de ejemplo
        return new ResponseEntity<>(List.of("Reporte 1", "Reporte 2"), HttpStatus.OK);
    }

    // Ejemplo de endpoint para crear un reporte de daño
    @PostMapping
    public ResponseEntity<String> createDamageReport(@RequestBody String report) {
        // Lógica de ejemplo
        return new ResponseEntity<>("Reporte creado: " + report, HttpStatus.CREATED);
    }
}

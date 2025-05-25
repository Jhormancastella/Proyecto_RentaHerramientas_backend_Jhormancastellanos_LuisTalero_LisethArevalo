package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentadeherramientas.rentadeherramientas.application.services.AlquilerService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Alquiler;
import com.rentadeherramientas.rentadeherramientas.dto.request.AlquilerRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AlquilerResponse;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @PostMapping
    public ResponseEntity<AlquilerResponse> crearAlquiler(@RequestBody AlquilerRequest request) {
        AlquilerResponse response = alquilerService.crearAlquiler(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object> obtenerAlquileresPorEstado(
            @RequestParam(required = false) String estado) {
        if (estado != null) {
            return ResponseEntity.ok(alquilerService.obtenerPorEstado(estado));
        }
        return ResponseEntity.ok(alquilerService.obtenerTodos());
    }
}
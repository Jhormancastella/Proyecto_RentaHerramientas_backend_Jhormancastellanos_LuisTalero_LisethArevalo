package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentadeherramientas.rentadeherramientas.application.services.ProveedorService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Proveedor;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return proveedorService.guardar(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.obtenerPorId(id)
            .map(p -> {
                p.setNombre(proveedor.getNombre());
                p.setContacto(proveedor.getContacto());
                p.setTelefono(proveedor.getTelefono());
                return ResponseEntity.ok(proveedorService.guardar(p));
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


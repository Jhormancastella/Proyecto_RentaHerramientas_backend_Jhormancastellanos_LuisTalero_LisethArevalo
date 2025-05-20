package com.rentadeherramientas.rentadeherramientas.controller;

import com.rentaherramientas.model.Herramienta;
import com.rentaherramientas.model.Proveedor;
import com.rentaherramientas.repository.HerramientaRepository;
import com.rentaherramientas.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public ResponseEntity<List<Herramienta>> listarHerramientas() {
        List<Herramienta> herramientas = herramientaRepository.findAll();
        return ResponseEntity.ok(herramientas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> obtenerHerramienta(@PathVariable Long id) {
        Optional<Herramienta> herramienta = herramientaRepository.findById(id);
        return herramienta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Herramienta>> listarPorCategoria(@PathVariable String categoria) {
        List<Herramienta> herramientas = herramientaRepository.findByCategoria(categoria);
        return ResponseEntity.ok(herramientas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Herramienta>> listarPorEstado(@PathVariable String estado) {
        List<Herramienta> herramientas = herramientaRepository.findByEstado(estado);
        return ResponseEntity.ok(herramientas);
    }

    @PostMapping
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<?> crearHerramienta(@RequestBody Herramienta herramienta) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Optional<Proveedor> proveedorOpt = proveedorRepository.findAll().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
        
        if (proveedorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Proveedor no encontrado");
        }
        
        herramienta.setProveedor(proveedorOpt.get());
        Herramienta nuevaHerramienta = herramientaRepository.save(herramienta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHerramienta);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<?> actualizarHerramienta(@PathVariable Long id, @RequestBody Herramienta herramientaActualizada) {
        Optional<Herramienta> herramientaOpt = herramientaRepository.findById(id);
        
        if (herramientaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Herramienta herramienta = herramientaOpt.get();
        
        // Verificar que el proveedor autenticado sea el dueño de la herramienta
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        if (!herramienta.getProveedor().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para editar esta herramienta");
        }
        
        herramienta.setNombre(herramientaActualizada.getNombre());
        herramienta.setDescripcion(herramientaActualizada.getDescripcion());
        herramienta.setPrecioDia(herramientaActualizada.getPrecioDia());
        herramienta.setEstado(herramientaActualizada.getEstado());
        herramienta.setCategoria(herramientaActualizada.getCategoria());
        herramienta.setImagenUrl(herramientaActualizada.getImagenUrl());
        
        Herramienta herramientaGuardada = herramientaRepository.save(herramienta);
        return ResponseEntity.ok(herramientaGuardada);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<?> eliminarHerramienta(@PathVariable Long id) {
        Optional<Herramienta> herramientaOpt = herramientaRepository.findById(id);
        
        if (herramientaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Herramienta herramienta = herramientaOpt.get();
        
        // Verificar que el proveedor autenticado sea el dueño de la herramienta
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        if (!herramienta.getProveedor().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar esta herramienta");
        }
        
        herramientaRepository.delete(herramienta);
        return ResponseEntity.ok().build();
    }
}

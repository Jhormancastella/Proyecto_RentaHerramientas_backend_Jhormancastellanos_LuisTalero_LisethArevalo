package com.rentadeherramientas.rentadeherramientas.controller;

import com.rentaherramientas.model.Cliente;
import com.rentaherramientas.model.Herramienta;
import com.rentaherramientas.model.Reserva;
import com.rentaherramientas.repository.ClienteRepository;
import com.rentaherramientas.repository.HerramientaRepository;
import com.rentaherramientas.repository.ReservaRepository;
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
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HerramientaRepository herramientaRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE', 'PROVEEDOR')")
    public ResponseEntity<?> obtenerReserva(@PathVariable Long id) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        
        if (reservaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Reserva reserva = reservaOpt.get();
        
        // Verificar permisos
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.ok(reserva);
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"))) {
            if (reserva.getCliente().getEmail().equals(email)) {
                return ResponseEntity.ok(reserva);
            }
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROVEEDOR"))) {
            if (reserva.getHerramienta().getProveedor().getEmail().equals(email)) {
                return ResponseEntity.ok(reserva);
            }
        }
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver esta reserva");
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        // Verificar que la herramienta exista
        Optional<Herramienta> herramientaOpt = herramientaRepository.findById(reserva.getHerramienta().getId());
        if (herramientaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Herramienta no encontrada");
        }
        
        Herramienta herramienta = herramientaOpt.get();
        
        // Verificar que la herramienta esté disponible
        if (!"DISPONIBLE".equals(herramienta.getEstado())) {
            return ResponseEntity.badRequest().body("La herramienta no está disponible");
        }
        
        // Asignar el cliente autenticado a la reserva
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Optional<Cliente> clienteOpt = clienteRepository.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
        
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente no encontrado");
        }
        
        reserva.setCliente(clienteOpt.get());
        reserva.setHerramienta(herramienta);
        reserva.setEstado("PENDIENTE");
        
        // Actualizar estado de la herramienta
        herramienta.setEstado("ALQUILADA");
        herramientaRepository.save(herramienta);
        
        Reserva nuevaReserva = reservaRepository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    public ResponseEntity<?> actualizarEstadoReserva(@PathVariable Long id, @RequestParam String nuevoEstado) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        
        if (reservaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Reserva reserva = reservaOpt.get();
        
        // Verificar permisos para proveedor
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROVEEDOR"))) {
            String email = auth.getName();
            if (!reserva.getHerramienta().getProveedor().getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para actualizar esta reserva");
            }
        }
        
        // Actualizar estado
        reserva.setEstado(nuevoEstado);
        
        // Si la reserva se cancela o se devuelve, actualizar estado de la herramienta
        if ("CANCELADA".equals(nuevoEstado) || "DEVUELTA".equals(nuevoEstado)) {
            Herramienta herramienta = reserva.getHerramienta();
            herramienta.setEstado("DISPONIBLE");
            herramientaRepository.save(herramienta);
        }
        
        Reserva reservaActualizada = reservaRepository.save(reserva);
        return ResponseEntity.ok(reservaActualizada);
    }

    @GetMapping("/cliente")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<Reserva>> listarReservasCliente() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Optional<Cliente> clienteOpt = clienteRepository.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
        
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        
        List<Reserva> reservas = reservaRepository.findByCliente(clienteOpt.get());
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/herramienta/{herramientaId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVEEDOR')")
    public ResponseEntity<?> listarReservasPorHerramienta(@PathVariable Long herramientaId) {
        Optional<Herramienta> herramientaOpt = herramientaRepository.findById(herramientaId);
        
        if (herramientaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Herramienta herramienta = herramientaOpt.get();
        
        // Verificar permisos para proveedor
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROVEEDOR"))) {
            String email = auth.getName();
            if (!herramienta.getProveedor().getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver estas reservas");
            }
        }
        
        List<Reserva> reservas = reservaRepository.findByHerramienta(herramienta);
        return ResponseEntity.ok(reservas);
    }
}

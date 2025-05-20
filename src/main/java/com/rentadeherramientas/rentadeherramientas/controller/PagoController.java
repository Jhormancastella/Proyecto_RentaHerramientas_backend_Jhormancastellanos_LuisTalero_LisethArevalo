package com.rentadeherramientas.rentadeherramientas.controller;

import com.rentaherramientas.model.Pago;
import com.rentaherramientas.model.Reserva;
import com.rentaherramientas.repository.PagoRepository;
import com.rentaherramientas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping("/{reservaId}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<?> realizarPago(@PathVariable Long reservaId, @RequestBody Pago pago) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);

        if (reservaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reserva reserva = reservaOpt.get();

        // Verificar que el cliente autenticado sea el dueño de la reserva
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        if (!reserva.getCliente().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para realizar este pago");
        }

        // Verificar si ya existe un pago para esta reserva
        Optional<Pago> pagoExistenteOpt = pagoRepository.findByReserva(reserva);
        if (pagoExistenteOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un pago para esta reserva");
        }

        // Configurar el pago
        pago.setReserva(reserva);
        pago.setFecha(LocalDate.now());
        pago.setMonto(reserva.getMontoTotal());
        pago.setEstado("PAGADO");

        // Actualizar estado de la reserva
        reserva.setEstado("ACEPTADA");
        reservaRepository.save(reserva);

        Pago nuevoPago = pagoRepository.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE', 'PROVEEDOR')")
    public ResponseEntity<?> obtenerPago(@PathVariable Long id) {
        Optional<Pago> pagoOpt = pagoRepository.findById(id);

        if (pagoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pago pago = pagoOpt.get();

        // Verificar permisos
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.ok(pago);
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"))) {
            if (pago.getReserva().getCliente().getEmail().equals(email)) {
                return ResponseEntity.ok(pago);
            }
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROVEEDOR"))) {
            if (pago.getReserva().getHerramienta().getProveedor().getEmail().equals(email)) {
                return ResponseEntity.ok(pago);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver este pago");
    }

    @GetMapping("/reserva/{reservaId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE', 'PROVEEDOR')")
    public ResponseEntity<?> obtenerPagoPorReserva(@PathVariable Long reservaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);

        if (reservaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reserva reserva = reservaOpt.get();

        // Verificar permisos
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            // Continuar
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"))) {
            if (!reserva.getCliente().getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver este pago");
            }
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROVEEDOR"))) {
            if (!reserva.getHerramienta().getProveedor().getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para ver este pago");
            }
        }

        Optional<Pago> pagoOpt = pagoRepository.findByReserva(reserva);
        return pagoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}

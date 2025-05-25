package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/payments")
public class paymentsController {

    @GetMapping
    public ResponseEntity<String> getAllPayments() {
        // Lógica para obtener todos los pagos
        return ResponseEntity.ok("Lista de pagos");
    }

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody String payment) {
        // Lógica para crear un nuevo pago
        return new ResponseEntity<>("Pago creado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPaymentById(@PathVariable Long id) {
        // Lógica para obtener un pago por ID
        return ResponseEntity.ok("Detalle del pago con ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody String payment) {
        // Lógica para actualizar un pago existente
        return ResponseEntity.ok("Pago actualizado con ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        // Lógica para eliminar un pago
        return ResponseEntity.ok("Pago eliminado con ID: " + id);
    }
}
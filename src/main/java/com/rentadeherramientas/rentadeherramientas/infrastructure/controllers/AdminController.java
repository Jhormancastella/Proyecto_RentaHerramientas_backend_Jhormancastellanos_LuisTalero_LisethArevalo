package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentadeherramientas.rentadeherramientas.application.services.UserService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Obtener usuarios por rol
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    // Obtener proveedores
    @GetMapping("/providers")
    public ResponseEntity<List<User>> getAllProviders() {
        return ResponseEntity.ok(userService.getUsersByRole("PROVIDER"));
    }

    // Obtener clientes
    @GetMapping("/clients")
    public ResponseEntity<List<User>> getAllClients() {
        return ResponseEntity.ok(userService.getUsersByRole("CLIENT"));
    }

    // Actualizar estado de usuario
    @PutMapping("/users/{id}/status")
    public ResponseEntity<User> updateUserStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        User user = userService.updateUserStatus(id, active);
        return ResponseEntity.ok(user);
    }

    // Eliminar usuario
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
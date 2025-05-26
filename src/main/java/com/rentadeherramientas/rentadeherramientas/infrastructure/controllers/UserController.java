package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import com.rentadeherramientas.rentadeherramientas.application.services.UserService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.findOneByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/dashboard/{username}", produces = "application/json")
public ResponseEntity<?> getDashboardData(@PathVariable String username) {
    try {
        User user = userService.findOneByUsername(username);

        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("user", user);
        dashboardData.put("firstName", user.getFirstName());
        dashboardData.put("lastName", user.getLastName());
        dashboardData.put("email", user.getEmail());
        dashboardData.put("roles", user.getRoles());
        dashboardData.put("active", user.isActive());

        return ResponseEntity.ok(dashboardData);
    } catch (UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Usuario no encontrado"));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Error en el servidor: " + e.getMessage()));
    }
}

    @PutMapping("/status/{id}")
    public ResponseEntity<User> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        try {
            User updatedUser = userService.updateUserStatus(id, active);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

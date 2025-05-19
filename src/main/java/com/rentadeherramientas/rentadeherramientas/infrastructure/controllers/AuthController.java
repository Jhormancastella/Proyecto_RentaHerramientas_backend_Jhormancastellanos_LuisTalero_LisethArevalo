package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import com.rentadeherramientas.rentadeherramientas.application.services.UserService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.dto.request.LoginRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
                )
            );
            
            final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
            
            // Here you would generate JWT token if using JWT authentication
            
            return ResponseEntity.ok(new AuthResponse("Login successful"));
            
        } catch (BadCredentialsException e) {
            return ResponseEntity
                .badRequest()
                .body(new AuthResponse("Invalid username or password"));
        }
    }  // <- Faltaba esta llave de cierre
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(new AuthResponse("User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .body(new AuthResponse("Registration failed: " + e.getMessage()));
        }
    }
}
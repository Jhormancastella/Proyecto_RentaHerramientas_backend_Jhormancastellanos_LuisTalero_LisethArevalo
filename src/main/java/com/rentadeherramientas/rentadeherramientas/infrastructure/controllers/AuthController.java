package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import com.rentadeherramientas.rentadeherramientas.application.services.JwtService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.dto.request.LoginRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AuthResponse;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
                )
            );
            
            final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
            
            final String jwt = jwtService.generateToken((User) userDetails, new HashMap<>());
            
            return ResponseEntity.ok(new AuthResponse(jwt));
            
        } catch (BadCredentialsException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse("Credenciales inválidas"));
        }
    }
}


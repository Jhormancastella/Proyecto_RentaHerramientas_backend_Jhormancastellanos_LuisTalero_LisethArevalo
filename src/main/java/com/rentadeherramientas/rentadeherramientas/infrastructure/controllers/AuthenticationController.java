package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentadeherramientas.rentadeherramientas.application.services.JwtService;
import com.rentadeherramientas.rentadeherramientas.application.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.rentadeherramientas.rentadeherramientas.dto.request.AuthenticationRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AuthenticationResponse;
import com.rentadeherramientas.rentadeherramientas.Config.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        
            var user = userService.findOneByUsername(request.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwtToken = jwtService.generateToken(userDetails, new HashMap<>());
        
            var response = new AuthenticationResponse();
            response.setJwt(jwtToken);
            response.setUsername(user.getUsername());
            response.setId(user.getId());
            response.setName(user.getFirstName() + " " + user.getLastName());
        
            // Asumiendo que user.getRoles() devuelve una lista o set
            var role = user.getRoles().stream().findFirst().orElseThrow(() -> 
                new RuntimeException("Usuario sin roles asignados"));
            response.setRole(role.getName());
        
            // También convertir los roles a una colección de strings para authorities
            Set<String> authorityNames = user.getRoles().stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toSet());
            response.setAuthorities(authorityNames);
        
            // (opcional, solo si se requiere por la interfaz, NO recomendable en prod)
            response.setPassword(user.getPassword());
        
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

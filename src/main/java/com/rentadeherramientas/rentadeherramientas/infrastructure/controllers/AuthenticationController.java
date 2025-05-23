package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.HashMap;

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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
            
            var user = userService.findOneByUsername(request.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwtToken = jwtService.generateToken(userDetails, new HashMap<>());
            
            var response = new AuthenticationResponse();
            response.setJwt(jwtToken);
            response.setUsername(user.getUsername());
            response.setId(user.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            return ResponseEntity.badRequest().build();
        }
    }
}

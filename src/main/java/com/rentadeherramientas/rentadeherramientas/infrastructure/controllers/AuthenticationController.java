package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentadeherramientas.rentadeherramientas.application.services.JwtService;
import com.rentadeherramientas.rentadeherramientas.application.services.UserService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.AuthenticationManager;
import com.rentadeherramientas.rentadeherramientas.domain.entity.UsernamePasswordAuthenticationToken;
import com.rentadeherramientas.rentadeherramientas.dto.request.AuthenticationRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        
        var user = userService.findOneByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user, new HashMap<>());
        
        var response = new AuthenticationResponse();
        response.setJwt(jwtToken);
        return ResponseEntity.ok(response);
    }
}

package com.rentadeherramientas.rentadeherramientas.domain.entity;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationManager {

    public void authenticate(com.rentadeherramientas.rentadeherramientas.domain.entity.UsernamePasswordAuthenticationToken authentication) {
        if (authentication == null) {
            throw new IllegalArgumentException("Authentication object cannot be null");
        }
        System.out.println("Usuario autenticado correctamente.");
    }

}

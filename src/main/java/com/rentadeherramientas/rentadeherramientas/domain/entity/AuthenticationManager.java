package com.rentadeherramientas.rentadeherramientas.domain.entity;

import org.springframework.security.core.Authentication;

public class AuthenticationManager {

    public void authenticate(Authentication authentication) {
        if (authentication == null) {
            throw new IllegalArgumentException("Authentication object cannot be null");
        }
        System.out.println("Usuario autenticado correctamente.");
    }

}

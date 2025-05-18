package com.rentadeherramientas.rentadeherramientas.application.services;

import java.util.Map;

import com.rentadeherramientas.rentadeherramientas.domain.entity.UserDetails;

public interface JwtService {

    String generateToken(org.springframework.security.core.userdetails.UserDetails user, Map<String, Object> extraClaims);

    String generateToken(UserDetails user, Map<String, Object> extraClaims);

    String extractUsername(String jwt);

    boolean validateToken(String jwt, org.springframework.security.core.userdetails.UserDetails userDetails);

}

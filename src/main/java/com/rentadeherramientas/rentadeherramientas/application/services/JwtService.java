package com.rentadeherramientas.rentadeherramientas.application.services;

import java.util.Map;
import java.util.function.Function;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.domain.entity.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(User user, Map<String, Object> extraClaims);

    String generateToken(UserDetails user, Map<String, Object> extraClaims);

    String extractUsername(String jwt);

    boolean validateToken(String jwt, org.springframework.security.core.userdetails.UserDetails userDetails);

    boolean isTokenExpired(String token);
    Claims extractAllClaims(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}

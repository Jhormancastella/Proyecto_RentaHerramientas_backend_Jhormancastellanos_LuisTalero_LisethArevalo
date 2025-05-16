package com.rentadeherramientas.rentadeherramientas.aplication.services;

import java.util.Map;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.domain.entity.UserDetails;

public interface JwtService {

    String generateToken(User user, Map<String, Object> extraClaims);

    String generateToken(UserDetails user, Map<String, Object> extraClaims);

    String extractUsername(String jwt);

    String generateToken(org.springframework.security.core.userdetails.UserDetails user,
            Map<String, Object> extraClaims);

}

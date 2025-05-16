package com.rentadeherramientas.rentadeherramientas.infraestructure.controllers;

import com.rentadeherramientas.rentadeherramientas.aplication.services.JwtUtil;
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
@CrossOrigin(origins = "*") // Cambiar por dominio real en producción
public class AuthController {


}
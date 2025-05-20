package com.rentadeherramientas.rentadeherramientas.dto.response;

import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private boolean activo;
    private Set<RoleName> roles;
}
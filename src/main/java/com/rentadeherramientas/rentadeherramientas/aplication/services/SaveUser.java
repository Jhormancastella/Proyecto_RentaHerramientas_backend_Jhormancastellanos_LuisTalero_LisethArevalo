package com.rentadeherramientas.rentadeherramientas.aplication.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

public interface SaveUser {
    void setId(Long id);

    void setName(String nombre);

    void setUsername(String username);

    void setRole(RoleName name);

    void setJwt(String jwt);

}

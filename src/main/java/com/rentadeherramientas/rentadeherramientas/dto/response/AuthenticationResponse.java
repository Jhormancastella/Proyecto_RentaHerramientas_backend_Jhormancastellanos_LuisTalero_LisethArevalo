package com.rentadeherramientas.rentadeherramientas.dto.response;

import com.rentadeherramientas.rentadeherramientas.aplication.services.RegisteredUser;
import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

public class AuthenticationResponse implements RegisteredUser {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    public void setName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public void setUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUsername'");
    }

    public void setRole(RoleName name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRole'");
    }

}

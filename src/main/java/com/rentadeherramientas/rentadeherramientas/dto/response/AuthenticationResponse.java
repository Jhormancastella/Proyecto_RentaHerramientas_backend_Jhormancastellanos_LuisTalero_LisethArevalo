package com.rentadeherramientas.rentadeherramientas.dto.response;


import java.util.Set;

import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

import nl._42.restsecure.autoconfigure.userdetails.RegisteredUser;

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

    @Override
    public Set<String> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

}

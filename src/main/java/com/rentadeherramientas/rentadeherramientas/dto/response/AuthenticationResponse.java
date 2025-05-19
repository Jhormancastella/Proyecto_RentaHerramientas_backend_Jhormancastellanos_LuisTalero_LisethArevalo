package com.rentadeherramientas.rentadeherramientas.dto.response;


import java.util.Set;

import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

import nl._42.restsecure.autoconfigure.userdetails.RegisteredUser;

public class AuthenticationResponse implements RegisteredUser {

    private String jwt;
    private Long id;
    private String name;
    private String username;
    private Set<String> authorities;
    private String password;
    private RoleName role;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(RoleName name) {
        this.role = name;
    }
}

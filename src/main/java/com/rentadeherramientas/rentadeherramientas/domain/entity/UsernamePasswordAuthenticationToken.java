package com.rentadeherramientas.rentadeherramientas.domain.entity;

public class UsernamePasswordAuthenticationToken {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

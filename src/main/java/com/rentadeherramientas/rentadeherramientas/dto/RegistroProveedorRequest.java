package com.rentaherramientas.dto;

public class RegistroProveedorRequest {
    private String nombre;
    private String email;
    private String password;
    private String empresa;
    private String contacto;

    // Constructores
    public RegistroProveedorRequest() {
    }

    public RegistroProveedorRequest(String nombre, String email, String password, String empresa, String contacto) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.empresa = empresa;
        this.contacto = contacto;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}

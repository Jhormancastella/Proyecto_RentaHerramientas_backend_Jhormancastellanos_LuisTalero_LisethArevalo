package com.rentadeherramientas.rentadeherramientas.model;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class Proveedor extends Usuario {
    private String empresa;
    private String contacto;

    // Constructores
    public Proveedor() {
    }

    public Proveedor(String nombre, String email, String password, String empresa, String contacto) {
        super(nombre, email, password, "PROVEEDOR");
        this.empresa = empresa;
        this.contacto = contacto;
    }

    // Getters y Setters
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

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(empresa, proveedor.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), empresa);
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", empresa='" + empresa + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}

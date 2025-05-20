package com.rentadeherramientas.rentadeherramientas.model;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class Cliente extends Usuario {
    private String telefono;
    private String direccion;

    // Constructores
    public Cliente() {
    }

    public Cliente(String nombre, String email, String password, String telefono, String direccion) {
        super(nombre, email, password, "CLIENTE");
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), telefono);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

package com.rentadeherramientas.rentadeherramientas.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Relaciones
    @OneToMany(mappedBy = "cliente")
    private List<Reservation> reservasHechas;

    @OneToMany(mappedBy = "proveedor")
    private List<Tool> herramientas; // Ahora usando la entidad Tool correcta

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Reservation> getReservasHechas() {
        return reservasHechas;
    }

    public void setReservasHechas(List<Reservation> reservasHechas) {
        this.reservasHechas = reservasHechas;
    }

    public List<Tool> getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(List<Tool> herramientas) {
        this.herramientas = herramientas;
    }

    // Métodos adicionales
    public String getNombre() {
        return this.username;
    }

    public void setNombre(String nombre) {
        this.username = nombre;
    }

    public void setRole(RoleName admin) {
        if (this.role == null) {
            this.role = new Role();
        }
        this.role.setName(admin);
    }

    public void setAdmin(boolean b) {
        if (this.role == null) {
            this.role = new Role();
        }
        this.role.setName(b ? RoleName.ADMIN : RoleName.CLIENTE);
    }

    public Collection<String> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        if (this.role != null && this.role.getName() != null) {
            authorities.add(this.role.getName().name());
        }
        return authorities;
    }

    public String getName() {
        return this.username;
    }
}
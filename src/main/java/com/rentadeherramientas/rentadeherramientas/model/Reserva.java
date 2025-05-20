package com.rentadeherramientas.rentadeherramientas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado; // "PENDIENTE", "ACEPTADA", "CANCELADA", "DEVUELTA"
    private double montoTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "herramienta_id")
    private Herramienta herramienta;

    // Constructores
    public Reserva() {
    }

    public Reserva(LocalDate fechaInicio, LocalDate fechaFin, String estado, 
                  Cliente cliente, Herramienta herramienta) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.cliente = cliente;
        this.herramienta = herramienta;
        // Calcular monto total basado en días y precio por día
        long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        this.montoTotal = dias * herramienta.getPrecioDia();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        recalcularMontoTotal();
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        recalcularMontoTotal();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
        recalcularMontoTotal();
    }

    // Método para recalcular el monto total
    private void recalcularMontoTotal() {
        if (fechaInicio != null && fechaFin != null && herramienta != null) {
            long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
            this.montoTotal = dias * herramienta.getPrecioDia();
        }
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                ", montoTotal=" + montoTotal +
                ", cliente=" + (cliente != null ? cliente.getId() : null) +
                ", herramienta=" + (herramienta != null ? herramienta.getId() : null) +
                '}';
    }
}

package com.rentadeherramientas.rentadeherramientas.domain.entity;

public class devoluciones {
    private Long id;
    private Long alquilerId;
    private String fechaDevolucion;
    private String estado;
    private String observaciones;

    public devoluciones() {
    }

    public devoluciones(Long id, Long alquilerId, String fechaDevolucion, String estado, String observaciones) {
        this.id = id;
        this.alquilerId = alquilerId;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlquilerId() {
        return alquilerId;
    }

    public void setAlquilerId(Long alquilerId) {
        this.alquilerId = alquilerId;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "alquileres")
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @NotNull
    @Column(nullable = false)
    private BigDecimal costoTotal;

    @Column(length = 20)
    private String estado = "PENDIENTE"; // PENDIENTE, ACTIVO, FINALIZADO, CANCELADO

    @Column(length = 500)
    private String observaciones;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}

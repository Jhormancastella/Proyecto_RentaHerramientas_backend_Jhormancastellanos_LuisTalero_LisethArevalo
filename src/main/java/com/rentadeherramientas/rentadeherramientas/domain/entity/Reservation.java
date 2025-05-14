package com.rentadeherramientas.rentadeherramientas.domain.entity;

import java.time.LocalDate;

import javax.tools.Tool;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment pago;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Invoice factura;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private DamageReport reporteDano;
}
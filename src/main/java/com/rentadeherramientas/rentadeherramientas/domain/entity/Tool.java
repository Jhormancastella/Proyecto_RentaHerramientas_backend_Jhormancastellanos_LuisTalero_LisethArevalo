package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tools")
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull
    @Positive
    private BigDecimal dailyRate;

    @NotNull
    private Boolean available = true;

    @Size(max = 100)
    private String brand;

    @Size(max = 50)
    private String model;

    @Size(max = 50)
    private String serialNumber;

    @Size(max = 100)
    private String category;

    @Size(max = 500)
    private String condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;
}
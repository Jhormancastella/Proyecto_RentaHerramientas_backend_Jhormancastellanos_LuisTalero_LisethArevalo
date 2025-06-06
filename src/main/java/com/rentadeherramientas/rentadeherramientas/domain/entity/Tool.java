package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Column(length = 100)
    private String name;

    @NotBlank
    @Column(length = 50, unique = true)
    private String serialNumber;

    @Column(length = 500)
    private String description;

    @NotNull
    @Positive
    private BigDecimal rentalPrice;

    @NotNull
    private Integer stock;

    private String category;

    @Column(length = 200)
    private String imageUrl;

    private boolean available = true;
}

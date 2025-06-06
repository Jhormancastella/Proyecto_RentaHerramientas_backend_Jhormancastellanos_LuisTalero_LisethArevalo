package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private Reservation reservation; // Relación con Reservation

    @NotNull
    @Positive
    private double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PaymentStatus status; // Enum: PENDING, COMPLETED, FAILED

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PaymentMethod method; // Enum: CREDIT_CARD, PAYPAL, CASH

    private String transactionId;

    @Column(length = 500)
    private String paymentDetails;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
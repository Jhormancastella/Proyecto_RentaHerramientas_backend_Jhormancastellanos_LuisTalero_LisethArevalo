package com.rentadeherramientas.rentadeherramientas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private Reservation reservation;

    @Column(name = "invoice_number", unique = true, nullable = false)
    private String invoiceNumber;

    @NotNull
    @Positive
    private BigDecimal subtotal;

    @NotNull
    @Positive
    private BigDecimal taxAmount;

    @NotNull
    @Positive
    private BigDecimal totalAmount;

    @Column(name = "issue_date")
    private LocalDateTime issueDate = LocalDateTime.now();

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InvoiceStatus status = InvoiceStatus.PENDING;

    @Column(length = 500)
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
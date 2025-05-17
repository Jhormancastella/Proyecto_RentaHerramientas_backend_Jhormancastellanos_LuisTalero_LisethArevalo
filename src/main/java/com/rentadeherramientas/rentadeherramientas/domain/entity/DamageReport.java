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
@Table(name = "damage_reports")
@NoArgsConstructor
@AllArgsConstructor
public class DamageReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @NotNull
    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private DamageLevel damageLevel;

    @Positive
    private BigDecimal repairCost;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private DamageStatus status = DamageStatus.REPORTED;

    @Column(length = 500)
    private String repairNotes;

    @Column(name = "report_date")
    private LocalDateTime reportDate = LocalDateTime.now();

    @Column(name = "repair_date")
    private LocalDateTime repairDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
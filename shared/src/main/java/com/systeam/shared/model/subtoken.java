package com.systeam.shared.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subtokens")
public class Subtoken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false, unique = true)
    private Proyecto proyecto;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(name = "suministro_total", nullable = false)
    private Integer suministroTotal;

    @Column(name = "cupo_restante", nullable = false)
    private Integer cupoRestante;

    // REGLA DE LA CÁTEDRA: Decimal para dinero
    @Column(name = "precio_actual", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioActual;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
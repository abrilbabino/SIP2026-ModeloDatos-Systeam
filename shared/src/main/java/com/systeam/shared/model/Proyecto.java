package com.systeam.shared.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "proyecto")
@SQLRestriction("deleted_at IS NULL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia al creador (FK: user_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario creador;

    @Column(nullable = false, length = 200)
    private String titulo;

    private String descripcion;

    @Column(name = "monto_requerido", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoRequerido;

    // Fecha límite para recolectar fondos
    @Column(nullable = false)
    private LocalDateTime plazo;

    @Column(nullable = false, length = 50)
    private String estado;

    // Indica si permite toma de decisiones por comunidad
    @Column(name = "gobernanza_comunidad", nullable = false)
    @Builder.Default
    private Boolean gobernanzaComunidad = false;

    // --- AUDITORÍA Y CONTROL ---

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Soft Delete: Almacena la fecha de eliminación lógica
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}

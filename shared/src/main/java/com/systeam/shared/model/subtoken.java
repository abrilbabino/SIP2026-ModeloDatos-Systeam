package com.systeam.backend.UserAdministration.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subtoken")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subtoken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false, unique = true)
    private Proyecto proyecto;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(name = "suministro_total", nullable = false)
    private Integer suministroTotal;

    @Column(name = "cupo_restante", nullable = false)
    private Integer cupoRestante;

    @Column(name = "precio_actual", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioActual;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
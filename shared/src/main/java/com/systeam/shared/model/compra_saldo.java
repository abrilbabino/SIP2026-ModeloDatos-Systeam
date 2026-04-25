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
@Table(name = "compra_saldo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Relación Muchos a Uno:
     * Un usuario puede realizar múltiples compras de saldo a lo largo del tiempo.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    @Column(name = "monto_idea", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoIdea;

    @Column(name = "monto_pagado_fiat", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoPagadoFiat;

    // HU33: Pasarela de pago (ej. Stripe, MercadoPago, Transferencia, etc.)
    @Column(name = "medio_pago", nullable = false, length = 100)
    private String medioPago;

    /*
     * Estado por defecto de la compra.
     * Para manejar estados como "pendiente" o "rechazada" en la pasarela
     * de pagos, se puede cambiar el estado por defecto o actualizarlo más adelante.
     */
    @Builder.Default
    @Column(nullable = false, length = 50)
    private String estado = "completada";

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
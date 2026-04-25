package com.systeam.backend.UserAdministration.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento_billetera")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoBilletera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Relación con el Usuario.
     * Registra a quién le pertenece este movimiento de dinero o tokens.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    /*
     * HU28/HU31 - Tipo de operación.
     * carga, inversion, dividendo, 
     * transferencia, recompensa, devolucion, compensacion.
     */
    @Column(name = "tipo_operacion", nullable = false, length = 50)
    private String tipoOperacion;

    /*
     * Moneda utilizada en el movimiento.
     * Valores definidos: IDEA, USDT, SUBTOKEN.
     */
    @Column(nullable = false, length = 20)
    private String moneda;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    // HU20: Hash de la transacción para trazabilidad en blockchain
    @Column(name = "tx_hash", length = 255)
    private String txHash;

    /*
     * Fecha del movimiento.
     * Solo usamos @CreationTimestamp porque los movimientos contables 
     * no suelen editarse, solo se crean (son inmutables).
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
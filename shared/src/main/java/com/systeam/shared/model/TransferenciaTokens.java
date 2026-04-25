package com.systeam.shared.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transferencias_tokens")
public class TransferenciaTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Quién envía
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisor;

    // Quién recibe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    // Trazabilidad de la Blockchain (Hash de la transacción)
    @Column(name = "tx_hash", length = 255)
    private String txHash;

    // Solo fecha de creación (Inmutable)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

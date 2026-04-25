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
@Table(name = "inversion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    private Proyecto proyecto;

    @Column(name = "monto_idea", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoIdea;

    @Column(name = "sub_tokens_recibidos", nullable = false)
    private Integer subTokensRecibidos;

    // HU10/HU11: Trazabilidad blockchain
    @Column(name = "tx_hash", length = 255)
    private String txHash;

    /*
     * Esta como DEFAULT 'completada'. 
     * @Builder.Default asegura que si creas un objeto con Lombok (el patrón Builder) 
     * y no le pasas un estado, arranque con "completada" por defecto.
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
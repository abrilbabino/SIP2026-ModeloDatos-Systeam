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
@Table(name = "wallet_swaps")
public class WalletSwap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "amount_idea", nullable = false, precision = 19, scale = 4)
    private BigDecimal amountIdea;

    @Column(name = "amount_usdc", nullable = false, precision = 19, scale = 4)
    private BigDecimal amountUsdc;

    @Column(name = "tx_hash", length = 66, nullable = false, unique = true)
    private String txHash;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

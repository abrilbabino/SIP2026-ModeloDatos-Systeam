package com.systeam.shared.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Nullable: usuarios OAuth2 no tienen password local
    @Column(nullable = true)
    private String password;

    @Column(nullable = false, length = 50)
    @Builder.Default
    private String provider = "local";

    @Column(name = "provider_id")
    private String providerId;

    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Rol> roles = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "saldo_idea", precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal saldoIdea = BigDecimal.ZERO;

    @Column(name = "saldo_usdt", precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal saldoUsdt = BigDecimal.ZERO;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_diversificador")
    @Builder.Default
    private Boolean isDiversificador = false;

    @Column(name = "nivel_inversor", length = 50)
    @Builder.Default
    private String nivelInversor = "STARTER";

    @Column(name = "kyc_status", length = 50)
    @Builder.Default
    private String kycStatus = "PENDING";

    @Column(name = "kyc_provider_id", length = 255)
    private String kycProviderId;
}

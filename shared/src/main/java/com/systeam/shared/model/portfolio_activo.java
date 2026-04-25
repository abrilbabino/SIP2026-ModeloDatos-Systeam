package com.systeam.backend.UserAdministration.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "portfolio_activo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioActivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Relación con el Usuario.
     * Un registro de portfolio pertenece a un solo usuario, 
     * pero un usuario puede tener muchos registros en su portfolio.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    /*
     * Relación con el Subtoken.
     * Este registro específico de portfolio apunta a un solo subtoken,
     * pero un subtoken puede estar en el portfolio de muchos usuarios.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subtoken_id", referencedColumnName = "id", nullable = false)
    private Subtoken subtoken;

    /*
     * Se definio DEFAULT 0.
     * Usamos @Builder.Default para que Lombok inicialice esta variable
     * automáticamente en 0 si no se especifica al crear el objeto.
     */
    @Builder.Default
    @Column(nullable = false)
    private Integer cantidad = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
package com.systeam.backend.UserAdministration.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Relación con el Usuario.
     * Define a quién va dirigida la notificación.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    @Column(nullable = false, length = 150)
    private String titulo;

    /*
     * Al ser tipo 'text' en SQL, usamos columnDefinition = "TEXT" 
     * para asegurar que soporte mensajes largos.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    /*
     * HU18: Indica si el usuario ya vio la notificación.
     * Definimos el valor por defecto como 'false' (no leída).
     */
    @Builder.Default
    @Column(nullable = false)
    private Boolean leida = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
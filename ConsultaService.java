package com.clinica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidade que armazena codigos de autenticacao em duas etapas (2FA).
 * Os codigos sao de 6 digitos e tem validade de 5 minutos.
 */
@Entity
@Table(name = "two_factor_codes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwoFactorCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 6)
    private String codigo;

    @Column(nullable = false)
    private LocalDateTime expiracao;

    @Builder.Default
    @Column(nullable = false)
    private Boolean utilizado = false;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    /**
     * Verifica se o codigo ainda e valido (nao expirado e nao utilizado)
     */
    public boolean isValido() {
        return !utilizado && LocalDateTime.now().isBefore(expiracao);
    }
}

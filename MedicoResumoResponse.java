package com.clinica.dto.auth;

import com.clinica.entity.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para resposta de autenticacao bem-sucedida.
 * Retorna o token JWT e informacoes do usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tipo;
    private Long usuarioId;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private String mensagem;

    public static AuthResponse sucesso(String token, Long usuarioId, String nome, 
                                       String email, TipoUsuario tipoUsuario) {
        return AuthResponse.builder()
                .token(token)
                .tipo("Bearer")
                .usuarioId(usuarioId)
                .nome(nome)
                .email(email)
                .tipoUsuario(tipoUsuario)
                .mensagem("Autenticacao realizada com sucesso")
                .build();
    }
}

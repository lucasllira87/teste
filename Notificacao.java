package com.clinica.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para resposta da primeira etapa de login (antes do 2FA).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String mensagem;
    private String email;
    private boolean codigoEnviado;

    public static LoginResponse sucesso(String email) {
        return LoginResponse.builder()
                .mensagem("Codigo de verificacao enviado para o seu email")
                .email(email)
                .codigoEnviado(true)
                .build();
    }
}

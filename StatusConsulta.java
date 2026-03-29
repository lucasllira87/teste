package com.clinica.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para verificacao do codigo 2FA (segunda etapa da autenticacao).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Verificar2FARequest {

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Codigo e obrigatorio")
    @Size(min = 6, max = 6, message = "Codigo deve ter 6 digitos")
    private String codigo;
}

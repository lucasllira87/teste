package com.clinica.dto.auth;

import com.clinica.entity.TipoUsuario;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para requisicao de cadastro de novo usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroRequest {

    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @Size(min = 6, message = "Senha deve ter no minimo 6 caracteres")
    private String senha;

    @NotNull(message = "Tipo de usuario e obrigatorio")
    private TipoUsuario tipoUsuario;

    // Campos opcionais
    private String telefone;
    private LocalDate dataNascimento;
    
    // Campos especificos para medicos
    private String especialidade;
    private String crm;
}

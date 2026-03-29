package com.clinica.dto.usuario;

import com.clinica.entity.TipoUsuario;
import com.clinica.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO para resposta com dados do usuario.
 * Usado para retornar informacoes sem expor a senha.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private String especialidade;
    private String crm;
    private String telefone;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private LocalDateTime dataCriacao;

    /**
     * Converte uma entidade Usuario para UsuarioResponse.
     */
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario())
                .especialidade(usuario.getEspecialidade())
                .crm(usuario.getCrm())
                .telefone(usuario.getTelefone())
                .dataNascimento(usuario.getDataNascimento())
                .ativo(usuario.getAtivo())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }
}

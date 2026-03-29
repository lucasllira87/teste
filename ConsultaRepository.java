package com.clinica.dto.usuario;

import com.clinica.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO resumido para listar medicos disponiveis.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResumoResponse {

    private Long id;
    private String nome;
    private String especialidade;
    private String crm;

    public static MedicoResumoResponse fromEntity(Usuario medico) {
        return MedicoResumoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .especialidade(medico.getEspecialidade())
                .crm(medico.getCrm())
                .build();
    }
}

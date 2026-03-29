package com.clinica.dto.consulta;

import com.clinica.dto.usuario.UsuarioResponse;
import com.clinica.entity.Consulta;
import com.clinica.entity.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para resposta com dados da consulta.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponse {

    private Long id;
    private LocalDateTime dataHora;
    private String descricao;
    private StatusConsulta status;
    private String motivoRecusa;
    private String observacoesMedico;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    // Dados do paciente
    private Long pacienteId;
    private String pacienteNome;
    private String pacienteEmail;
    private String pacienteTelefone;
    
    // Dados do medico (pode ser null)
    private Long medicoId;
    private String medicoNome;
    private String medicoEspecialidade;
    private String medicoCrm;

    /**
     * Converte uma entidade Consulta para ConsultaResponse.
     */
    public static ConsultaResponse fromEntity(Consulta consulta) {
        ConsultaResponse.ConsultaResponseBuilder builder = ConsultaResponse.builder()
                .id(consulta.getId())
                .dataHora(consulta.getDataHora())
                .descricao(consulta.getDescricao())
                .status(consulta.getStatus())
                .motivoRecusa(consulta.getMotivoRecusa())
                .observacoesMedico(consulta.getObservacoesMedico())
                .dataCriacao(consulta.getDataCriacao())
                .dataAtualizacao(consulta.getDataAtualizacao())
                .pacienteId(consulta.getPaciente().getId())
                .pacienteNome(consulta.getPaciente().getNome())
                .pacienteEmail(consulta.getPaciente().getEmail())
                .pacienteTelefone(consulta.getPaciente().getTelefone());

        if (consulta.getMedico() != null) {
            builder.medicoId(consulta.getMedico().getId())
                    .medicoNome(consulta.getMedico().getNome())
                    .medicoEspecialidade(consulta.getMedico().getEspecialidade())
                    .medicoCrm(consulta.getMedico().getCrm());
        }

        return builder.build();
    }
}

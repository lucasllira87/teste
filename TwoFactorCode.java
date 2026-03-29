package com.clinica.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para requisicao de criacao de nova consulta.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaRequest {

    @NotNull(message = "Data e hora sao obrigatorias")
    @Future(message = "A data da consulta deve ser futura")
    private LocalDateTime dataHora;

    private String descricao;

    // Opcional - paciente pode escolher o medico
    private Long medicoId;
}

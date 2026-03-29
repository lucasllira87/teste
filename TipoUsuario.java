package com.clinica.dto.consulta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisicao de aceite de consulta.
 * Permite atribuir um medico caso nao tenha sido escolhido pelo paciente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AceitarConsultaRequest {

    // ID do medico a ser atribuido (obrigatorio se paciente nao escolheu)
    private Long medicoId;
}

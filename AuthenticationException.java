package com.clinica.dto.consulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisicao de recusa de consulta.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecusaConsultaRequest {

    private String motivoRecusa;
}

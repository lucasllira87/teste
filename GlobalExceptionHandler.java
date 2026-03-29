package com.clinica.dto.notificacao;

import com.clinica.entity.Notificacao;
import com.clinica.entity.TipoNotificacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para resposta com dados da notificacao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoResponse {

    private Long id;
    private String mensagem;
    private TipoNotificacao tipo;
    private Boolean lida;
    private Long consultaId;
    private LocalDateTime dataCriacao;

    /**
     * Converte uma entidade Notificacao para NotificacaoResponse.
     */
    public static NotificacaoResponse fromEntity(Notificacao notificacao) {
        return NotificacaoResponse.builder()
                .id(notificacao.getId())
                .mensagem(notificacao.getMensagem())
                .tipo(notificacao.getTipo())
                .lida(notificacao.getLida())
                .consultaId(notificacao.getConsulta() != null ? notificacao.getConsulta().getId() : null)
                .dataCriacao(notificacao.getDataCriacao())
                .build();
    }
}

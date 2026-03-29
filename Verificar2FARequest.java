package com.clinica.controller;

import com.clinica.dto.ApiResponse;
import com.clinica.dto.notificacao.NotificacaoResponse;
import com.clinica.entity.Usuario;
import com.clinica.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para operacoes gerais de notificacoes.
 * Acessivel por todos os usuarios autenticados.
 */
@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
@Slf4j
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    /**
     * PUT /notificacoes/{id}/marcar-lida
     * Marca uma notificacao especifica como lida.
     */
    @PutMapping("/{id}/marcar-lida")
    public ResponseEntity<ApiResponse<NotificacaoResponse>> marcarComoLida(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long id) {
        
        log.info("Usuario {} marcando notificacao {} como lida", usuario.getEmail(), id);
        
        NotificacaoResponse response = notificacaoService.marcarComoLida(id, usuario.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacao marcada como lida", response)
        );
    }

    /**
     * PUT /notificacoes/marcar-todas-lidas
     * Marca todas as notificacoes do usuario como lidas.
     */
    @PutMapping("/marcar-todas-lidas")
    public ResponseEntity<ApiResponse<Integer>> marcarTodasComoLidas(
            @AuthenticationPrincipal Usuario usuario) {
        
        log.info("Usuario {} marcando todas notificacoes como lidas", usuario.getEmail());
        
        int quantidade = notificacaoService.marcarTodasComoLidas(usuario.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes marcadas como lidas", quantidade)
        );
    }

    /**
     * GET /notificacoes/contador
     * Retorna a quantidade de notificacoes nao lidas.
     */
    @GetMapping("/contador")
    public ResponseEntity<ApiResponse<Long>> contarNaoLidas(
            @AuthenticationPrincipal Usuario usuario) {
        
        Long quantidade = notificacaoService.contarNotificacoesNaoLidas(usuario.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Contador de notificacoes", quantidade)
        );
    }
}

package com.clinica.controller;

import com.clinica.dto.ApiResponse;
import com.clinica.dto.consulta.AceitarConsultaRequest;
import com.clinica.dto.consulta.ConsultaResponse;
import com.clinica.dto.consulta.RecusaConsultaRequest;
import com.clinica.dto.notificacao.NotificacaoResponse;
import com.clinica.dto.usuario.MedicoResumoResponse;
import com.clinica.entity.Usuario;
import com.clinica.service.ConsultaService;
import com.clinica.service.NotificacaoService;
import com.clinica.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para endpoints do dashboard da RECEPCAO.
 * Permite listar consultas pendentes, aceitar ou recusar.
 */
@RestController
@RequestMapping("/recepcao")
@RequiredArgsConstructor
@Slf4j
public class RecepcaoController {

    private final ConsultaService consultaService;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;

    /**
     * GET /recepcao/consultas/pendentes
     * Lista todas as consultas com status PENDENTE.
     */
    @GetMapping("/consultas/pendentes")
    public ResponseEntity<ApiResponse<List<ConsultaResponse>>> listarConsultasPendentes(
            @AuthenticationPrincipal Usuario recepcao) {
        
        log.info("Recepcao {} listando consultas pendentes", recepcao.getEmail());
        
        List<ConsultaResponse> consultas = consultaService.listarConsultasPendentes();
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consultas pendentes listadas com sucesso", consultas)
        );
    }

    /**
     * PUT /recepcao/consultas/{id}/aceitar
     * Aceita uma consulta pendente.
     */
    @PutMapping("/consultas/{id}/aceitar")
    public ResponseEntity<ApiResponse<ConsultaResponse>> aceitarConsulta(
            @AuthenticationPrincipal Usuario recepcao,
            @PathVariable Long id,
            @RequestBody(required = false) AceitarConsultaRequest request) {
        
        log.info("Recepcao {} aceitando consulta {}", recepcao.getEmail(), id);
        
        ConsultaResponse response = consultaService.aceitarConsulta(id, request);
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consulta aceita com sucesso", response)
        );
    }

    /**
     * PUT /recepcao/consultas/{id}/recusar
     * Recusa uma consulta pendente.
     */
    @PutMapping("/consultas/{id}/recusar")
    public ResponseEntity<ApiResponse<ConsultaResponse>> recusarConsulta(
            @AuthenticationPrincipal Usuario recepcao,
            @PathVariable Long id,
            @RequestBody(required = false) RecusaConsultaRequest request) {
        
        log.info("Recepcao {} recusando consulta {}", recepcao.getEmail(), id);
        
        ConsultaResponse response = consultaService.recusarConsulta(id, request);
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consulta recusada", response)
        );
    }

    /**
     * GET /recepcao/notificacoes
     * Lista todas as notificacoes da recepcao.
     */
    @GetMapping("/notificacoes")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoes(
            @AuthenticationPrincipal Usuario recepcao) {
        
        log.info("Recepcao {} listando notificacoes", recepcao.getEmail());
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoes(recepcao.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes listadas com sucesso", notificacoes)
        );
    }

    /**
     * GET /recepcao/notificacoes/nao-lidas
     * Lista notificacoes nao lidas.
     */
    @GetMapping("/notificacoes/nao-lidas")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoesNaoLidas(
            @AuthenticationPrincipal Usuario recepcao) {
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoesNaoLidas(recepcao.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes nao lidas listadas", notificacoes)
        );
    }

    /**
     * GET /recepcao/medicos
     * Lista medicos disponiveis para atribuir a consultas.
     */
    @GetMapping("/medicos")
    public ResponseEntity<ApiResponse<List<MedicoResumoResponse>>> listarMedicos() {
        
        List<MedicoResumoResponse> medicos = usuarioService.listarMedicos();
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Medicos listados com sucesso", medicos)
        );
    }
}

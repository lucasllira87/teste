package com.clinica.controller;

import com.clinica.dto.ApiResponse;
import com.clinica.dto.consulta.ConsultaRequest;
import com.clinica.dto.consulta.ConsultaResponse;
import com.clinica.dto.notificacao.NotificacaoResponse;
import com.clinica.dto.usuario.MedicoResumoResponse;
import com.clinica.entity.Usuario;
import com.clinica.service.ConsultaService;
import com.clinica.service.NotificacaoService;
import com.clinica.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para endpoints do dashboard do PACIENTE.
 * Permite criar consultas, listar suas consultas e notificacoes.
 */
@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Slf4j
public class PacienteController {

    private final ConsultaService consultaService;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;

    /**
     * POST /paciente/consultas
     * Cria uma nova solicitacao de consulta.
     */
    @PostMapping("/consultas")
    public ResponseEntity<ApiResponse<ConsultaResponse>> criarConsulta(
            @AuthenticationPrincipal Usuario paciente,
            @Valid @RequestBody ConsultaRequest request) {
        
        log.info("Paciente {} criando consulta", paciente.getEmail());
        
        ConsultaResponse response = consultaService.criarConsulta(paciente.getId(), request);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.sucesso("Consulta solicitada com sucesso", response));
    }

    /**
     * GET /paciente/consultas
     * Lista todas as consultas do paciente logado.
     */
    @GetMapping("/consultas")
    public ResponseEntity<ApiResponse<List<ConsultaResponse>>> listarConsultas(
            @AuthenticationPrincipal Usuario paciente) {
        
        log.info("Paciente {} listando consultas", paciente.getEmail());
        
        List<ConsultaResponse> consultas = consultaService.listarConsultasPaciente(paciente.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consultas listadas com sucesso", consultas)
        );
    }

    /**
     * GET /paciente/notificacoes
     * Lista todas as notificacoes do paciente (nao lidas primeiro).
     */
    @GetMapping("/notificacoes")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoes(
            @AuthenticationPrincipal Usuario paciente) {
        
        log.info("Paciente {} listando notificacoes", paciente.getEmail());
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoes(paciente.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes listadas com sucesso", notificacoes)
        );
    }

    /**
     * GET /paciente/notificacoes/nao-lidas
     * Lista apenas notificacoes nao lidas.
     */
    @GetMapping("/notificacoes/nao-lidas")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoesNaoLidas(
            @AuthenticationPrincipal Usuario paciente) {
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoesNaoLidas(paciente.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes nao lidas listadas", notificacoes)
        );
    }

    /**
     * GET /paciente/medicos
     * Lista medicos disponiveis para escolha ao criar consulta.
     */
    @GetMapping("/medicos")
    public ResponseEntity<ApiResponse<List<MedicoResumoResponse>>> listarMedicos() {
        
        List<MedicoResumoResponse> medicos = usuarioService.listarMedicos();
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Medicos listados com sucesso", medicos)
        );
    }
}

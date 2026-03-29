package com.clinica.controller;

import com.clinica.dto.ApiResponse;
import com.clinica.dto.consulta.ConsultaResponse;
import com.clinica.dto.notificacao.NotificacaoResponse;
import com.clinica.dto.usuario.UsuarioResponse;
import com.clinica.entity.Usuario;
import com.clinica.service.ConsultaService;
import com.clinica.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para endpoints do dashboard do MEDICO.
 * Permite listar consultas aceitas e pacientes.
 */
@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
@Slf4j
public class MedicoController {

    private final ConsultaService consultaService;
    private final NotificacaoService notificacaoService;

    /**
     * GET /medico/consultas
     * Lista todas as consultas ACEITAS atribuidas ao medico logado.
     */
    @GetMapping("/consultas")
    public ResponseEntity<ApiResponse<List<ConsultaResponse>>> listarConsultas(
            @AuthenticationPrincipal Usuario medico) {
        
        log.info("Medico {} listando consultas aceitas", medico.getEmail());
        
        List<ConsultaResponse> consultas = consultaService.listarConsultasAceitasMedico(medico.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consultas listadas com sucesso", consultas)
        );
    }

    /**
     * GET /medico/consultas/todas
     * Lista todas as consultas do medico (todos os status).
     */
    @GetMapping("/consultas/todas")
    public ResponseEntity<ApiResponse<List<ConsultaResponse>>> listarTodasConsultas(
            @AuthenticationPrincipal Usuario medico) {
        
        log.info("Medico {} listando todas as consultas", medico.getEmail());
        
        List<ConsultaResponse> consultas = consultaService.listarConsultasMedico(medico.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Consultas listadas com sucesso", consultas)
        );
    }

    /**
     * GET /medico/pacientes
     * Lista pacientes que tem consultas aceitas com o medico.
     */
    @GetMapping("/pacientes")
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listarPacientes(
            @AuthenticationPrincipal Usuario medico) {
        
        log.info("Medico {} listando pacientes", medico.getEmail());
        
        List<UsuarioResponse> pacientes = consultaService.listarPacientesMedico(medico.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Pacientes listados com sucesso", pacientes)
        );
    }

    /**
     * GET /medico/notificacoes
     * Lista todas as notificacoes do medico.
     */
    @GetMapping("/notificacoes")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoes(
            @AuthenticationPrincipal Usuario medico) {
        
        log.info("Medico {} listando notificacoes", medico.getEmail());
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoes(medico.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes listadas com sucesso", notificacoes)
        );
    }

    /**
     * GET /medico/notificacoes/nao-lidas
     * Lista notificacoes nao lidas.
     */
    @GetMapping("/notificacoes/nao-lidas")
    public ResponseEntity<ApiResponse<List<NotificacaoResponse>>> listarNotificacoesNaoLidas(
            @AuthenticationPrincipal Usuario medico) {
        
        List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoesNaoLidas(medico.getId());
        
        return ResponseEntity.ok(
            ApiResponse.sucesso("Notificacoes nao lidas listadas", notificacoes)
        );
    }
}

package com.clinica.controller;

import com.clinica.dto.ApiResponse;
import com.clinica.dto.auth.*;
import com.clinica.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para endpoints de autenticacao.
 * Implementa cadastro, login (2FA) e verificacao.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * POST /auth/cadastro
     * Cadastra um novo usuario no sistema.
     */
    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<AuthResponse>> cadastrar(
            @Valid @RequestBody CadastroRequest request) {
        
        log.info("Requisicao de cadastro recebida para: {}", request.getEmail());
        
        AuthResponse response = authService.cadastrar(request);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.sucesso("Usuario cadastrado com sucesso", response));
    }

    /**
     * POST /auth/login
     * Primeira etapa do login - valida credenciais e envia codigo 2FA por email.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        
        log.info("Requisicao de login recebida para: {}", request.getEmail());
        
        LoginResponse response = authService.login(request);
        
        return ResponseEntity.ok(ApiResponse.sucesso("Codigo 2FA enviado com sucesso", response));
    }

    /**
     * POST /auth/verificar-2fa
     * Segunda etapa do login - valida codigo 2FA e retorna token JWT.
     */
    @PostMapping("/verificar-2fa")
    public ResponseEntity<ApiResponse<AuthResponse>> verificar2FA(
            @Valid @RequestBody Verificar2FARequest request) {
        
        log.info("Requisicao de verificacao 2FA para: {}", request.getEmail());
        
        AuthResponse response = authService.verificar2FA(request);
        
        return ResponseEntity.ok(ApiResponse.sucesso("Login realizado com sucesso", response));
    }
}

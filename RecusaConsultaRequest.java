package com.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO generico para respostas da API.
 * Utilizado para padronizar respostas de sucesso e erro.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean sucesso;
    private String mensagem;
    private T dados;
    private LocalDateTime timestamp;

    /**
     * Cria uma resposta de sucesso com dados.
     */
    public static <T> ApiResponse<T> sucesso(String mensagem, T dados) {
        return ApiResponse.<T>builder()
                .sucesso(true)
                .mensagem(mensagem)
                .dados(dados)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Cria uma resposta de sucesso sem dados.
     */
    public static <T> ApiResponse<T> sucesso(String mensagem) {
        return ApiResponse.<T>builder()
                .sucesso(true)
                .mensagem(mensagem)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Cria uma resposta de erro.
     */
    public static <T> ApiResponse<T> erro(String mensagem) {
        return ApiResponse.<T>builder()
                .sucesso(false)
                .mensagem(mensagem)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

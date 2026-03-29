package com.clinica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuracao para habilitar metodos assincronos.
 * Usado principalmente para envio de emails.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    // A configuracao padrao do Spring Boot e suficiente para a maioria dos casos.
    // Customizacoes adicionais podem ser adicionadas aqui se necessario.
}

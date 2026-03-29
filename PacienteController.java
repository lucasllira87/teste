package com.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicacao Clinica Medica
 * Sistema completo com autenticacao JWT, 2FA, e dashboards para
 * Pacientes, Recepcao e Medicos.
 * 
 * @author Sistema Clinica Medica
 * @version 1.0.0
 */
@SpringBootApplication
public class ClinicaMedicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaApplication.class, args);
        System.out.println("===========================================");
        System.out.println("   CLINICA MEDICA - Sistema Iniciado!");
        System.out.println("   Acesse: http://localhost:8080");
        System.out.println("===========================================");
    }
}

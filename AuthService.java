package com.clinica.entity;

/**
 * Enum que define os tipos de usuarios do sistema.
 * Cada tipo possui permissoes especificas e acesso a dashboards diferentes.
 */
public enum TipoUsuario {
    
    /**
     * Medico - Acesso ao dashboard medico
     * Pode visualizar consultas aceitas e pacientes
     */
    MEDICO("ROLE_MEDICO"),
    
    /**
     * Recepcao - Acesso ao dashboard de recepcao
     * Pode aceitar ou recusar consultas pendentes
     */
    RECEPCAO("ROLE_RECEPCAO"),
    
    /**
     * Paciente - Acesso ao dashboard do paciente
     * Pode solicitar consultas e visualizar status
     */
    PACIENTE("ROLE_PACIENTE");
    
    private final String role;
    
    TipoUsuario(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}

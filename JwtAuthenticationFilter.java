package com.clinica.entity;

/**
 * Enum que define os possiveis status de uma consulta.
 */
public enum StatusConsulta {
    
    /**
     * Consulta aguardando aprovacao da recepcao
     */
    PENDENTE,
    
    /**
     * Consulta aceita pela recepcao
     */
    ACEITA,
    
    /**
     * Consulta recusada pela recepcao
     */
    RECUSADA,
    
    /**
     * Consulta cancelada pelo paciente ou sistema
     */
    CANCELADA,
    
    /**
     * Consulta realizada com sucesso
     */
    CONCLUIDA
}

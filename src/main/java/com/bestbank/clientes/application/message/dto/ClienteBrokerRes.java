package com.bestbank.clientes.application.message.dto;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoEstadoFinaciero;
import lombok.Data;

/**
 * Clase que representa la respuesta de un cliente.
 */
@Data
public class ClienteBrokerRes {
  
  private String codCtrlBroker;
  
  private String id;
  
  private String nombres;
  
  private String apellidos;
  
  private String estado;
  
  private TipoCliente tipoCliente;
  
  private TipoEstadoFinaciero estadoFinanciero;
  
}

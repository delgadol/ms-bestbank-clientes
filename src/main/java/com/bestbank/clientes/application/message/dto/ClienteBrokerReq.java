package com.bestbank.clientes.application.message.dto;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;
import lombok.Data;

/**
 * Representa una solicitud de cliente.
 * La clase ClienteReq es utilizada para enviar información relacionada a un cliente.
 */
@Data
public class ClienteBrokerReq {
  
  private String codCtrlBroker;
  
  private String codCliente;
  
  private TipoCliente tipoCliente;
  
  private TipoDocumento tipoDocumento;
  
  private String numDocumento;
  
  private String nombres;
  
  private String apellidos;
  
  private Integer indMonedero;  
  
}

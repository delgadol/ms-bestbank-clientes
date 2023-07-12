package com.bestbank.clientes.presentation.dto;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Representa una solicitud de cliente.
 * La clase ClienteReq es utilizada para enviar información relacionada a un cliente.
 */
@Data
public class ClienteReq {
  
  @NotNull
  private TipoCliente tipoCliente;
  
  @NotNull
  private TipoDocumento tipoDocumento;
  
  @NotEmpty
  private String numDocumento;
  
  @NotEmpty
    private String nombres;
  
  
  @NotEmpty
    private String apellidos;
  
  
}

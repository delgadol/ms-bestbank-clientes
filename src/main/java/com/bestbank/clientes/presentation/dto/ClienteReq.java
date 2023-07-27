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
  
  @NotNull(message = "Tipo de Cliente requerido")
  private TipoCliente tipoCliente;
  
  @NotNull(message = "Tipo de Documento requerido")
  private TipoDocumento tipoDocumento;
  
  @NotEmpty(message = "Numero de Dcocumento requerido")
  private String numDocumento;
  
  @NotEmpty(message = "Nombre de Cliente es requerido")
    private String nombres;
  
  @NotEmpty(message = "Apellido de Cliente requerido")
  private String apellidos;
  
  @NotNull(message = "Es cliente Monedero")
  private Integer indMonedero;  
  
}

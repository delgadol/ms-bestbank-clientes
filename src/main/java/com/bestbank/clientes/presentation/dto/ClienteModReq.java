package com.bestbank.clientes.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Clase que representa la solicitud de modificación de un cliente.
 */
@Data
public class ClienteModReq {
  
  @NotEmpty
  private String nombres;
  
  @NotEmpty
  private String apellidos;

}
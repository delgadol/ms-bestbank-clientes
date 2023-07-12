package com.bestbank.clientes.presentation.dto;

import com.bestbank.clientes.domain.utils.TipoCliente;

import lombok.Data;

@Data
public class ClienteRes {
  
  private String id;
  
  private String nombres;
  
  private String apellidos;
  
  private String estado;
  
  private TipoCliente tipoCliente;
  
}

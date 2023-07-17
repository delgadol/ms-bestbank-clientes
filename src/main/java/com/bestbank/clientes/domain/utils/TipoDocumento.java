package com.bestbank.clientes.domain.utils;

/**
 * Enumeración que define los diferentes tipos de documentos de identificación.
 */
public enum TipoDocumento {
  
  RUC("REgistro Unico de Contribuyente"),
  DNI("Documento Nacional de Identidad");
  
  private String descripcion;
  
  TipoDocumento(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

}

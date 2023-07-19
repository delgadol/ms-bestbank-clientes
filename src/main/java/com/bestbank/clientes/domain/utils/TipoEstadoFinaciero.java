package com.bestbank.clientes.domain.utils;

public enum TipoEstadoFinaciero {
  
  SOLVENTE("SOLVENTE"),
  DEUDOR("DEUDOR");
  
  TipoEstadoFinaciero(String descripcion) {
    this.descripcion = descripcion;
  }

  private String descripcion;

  public String getDescripcion() {
    return descripcion;
  }
  

}

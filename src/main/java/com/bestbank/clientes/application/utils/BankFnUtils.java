package com.bestbank.clientes.application.utils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Clase que proporciona funciones y utilidades relacionadas con operaciones bancarias.
 */
public class BankFnUtils {
  
  private BankFnUtils() {
    
  }
  
  /**
   * Genera un código único para identificar un producto.
   *
   * @return El código único generado.
   */
  public static String uniqueProductCode() {
    
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  
  }
  
  public static java.sql.Timestamp getDateTime() {
    return java.sql.Timestamp.valueOf(LocalDateTime.now());
  }
  
}

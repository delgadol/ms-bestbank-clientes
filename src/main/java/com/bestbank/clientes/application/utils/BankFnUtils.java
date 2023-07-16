package com.bestbank.clientes.application.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankFnUtils {
  
  private BankFnUtils(){
    
  }
  
  public static String uniqueProductCode() {
    
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  
  }
  
  public static java.sql.Timestamp getDateTime() {
    return java.sql.Timestamp.valueOf(LocalDateTime.now());
  }
  
}

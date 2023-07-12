package com.bestbank.clientes.application.utils;

import java.util.UUID;

public class BankFnUtils {
  
  private BankFnUtils(){
    
  }
  
  public static String uniqueProductCode() {
    
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  
  }
}

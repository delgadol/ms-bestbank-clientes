package com.bestbank.clientes.infrastructure.errores;

import org.springframework.validation.BindingResult;

/**
 * Clase de excepción que se lanza cuando se encuentran datos inválidos o incorrectos.
 */
public class InvalidDataException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final transient BindingResult result;

  public InvalidDataException(BindingResult result) {
    super();
    this.result = result;
  }

  public InvalidDataException(String message, BindingResult result) {
    super(message);
    this.result = result;
  }

  public BindingResult getResult() {
    return result;
  }
}
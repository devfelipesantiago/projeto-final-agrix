package com.betrybe.agrix.ebytr.staff.exception;

/**
 * Exceção personalizada que indica que o recurso não foi encontrado. Extende a
 * classe
 * {@link Exception}.
 */
public class NotFoundException extends Exception {

  /**
   * Construtor da exceção NotFoundException com uma mensagem de erro.
   *
   * @param message A mensagem de erro que descreve o motivo da exceção.
   */
  public NotFoundException(String message) {
    super(message);
  }

  /**
   * Construtor da exceção NotFoundException com uma mensagem de erro e a causa.
   *
   * @param message A mensagem de erro que descreve o motivo da exceção.
   * @param cause   A causa que originou a exceção.
   */
  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}

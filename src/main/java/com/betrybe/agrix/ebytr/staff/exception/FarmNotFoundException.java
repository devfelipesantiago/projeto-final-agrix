package com.betrybe.agrix.ebytr.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção personalizada que indica que a fazenda não foi encontrada. A anotação
 * {@link ResponseStatus} define o código HTTP 404 (NOT_FOUND) para esta
 * exceção.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FarmNotFoundException extends NotFoundException {

  /**
   * Construtor da exceção FarmNotFoundException com uma mensagem padrão. A
   * mensagem é "Fazenda não
   * encontrada!".
   */
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}

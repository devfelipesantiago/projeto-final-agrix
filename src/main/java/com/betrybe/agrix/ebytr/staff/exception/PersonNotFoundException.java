package com.betrybe.agrix.ebytr.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma pessoa não é encontrada.
 * Extende a classe {@link RuntimeException} para indicar um erro que ocorre
 * durante a execução.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

  /**
   * Construtor da exceção sem parâmetros, que define a mensagem padrão de erro.
   */
  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}

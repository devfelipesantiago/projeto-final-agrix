package com.betrybe.agrix.ebytr.staff.exception;

/**
 * Exceção lançada quando uma plantação não é encontrada no sistema.
 * Esta classe estende {@link NotFoundException} para representar um erro
 * específico
 * de ausência de plantação.
 */
public class CropNotFoundException extends NotFoundException {

  /**
   * Construtor da exceção CropNotFoundException com uma mensagem padrão. A
   * mensagem é "Plantação
   * não encontrada.".
   */
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}

package com.betrybe.agrix.ebytr.staff.controlleradvice;

import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador de exceções global para tratar diferentes tipos de erros no sistema. A classe usa a
 * anotação {@link ControllerAdvice} para fornecer uma lógica centralizada de tratamento de
 * exceções.
 */

@ControllerAdvice
public class ExceptionController {

  /**
   * Manipula exceções do tipo {@link FarmNotFoundException} e {@link CropNotFoundException}.
   * Retorna uma resposta com status HTTP 404 (NOT_FOUND) e a mensagem da exceção.
   *
   * @param exception A exceção capturada.
   * @return A resposta com o status e mensagem da exceção.
   */
  @ExceptionHandler({FarmNotFoundException.class, CropNotFoundException.class,
      FertilizerNotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  /**
   * Manipula exceções do tipo {@link AccessDeniedException}, que ocorrem quando um usuário
   * autenticado tenta acessar um recurso para o qual não tem permissões suficientes.
   * Retorna uma resposta com status HTTP 403 (FORBIDDEN) e uma mensagem de "Access Denied".
   *
   * @param exception A exceção {@link AccessDeniedException} capturada.
   * @return Um {@link ResponseEntity} com status 403 e mensagem "Access Denied".
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body("Access Denied");
  }

  /**
   * Manipula exceções do tipo {@link BadCredentialsException}, que ocorrem quando um usuário
   * autenticado tenta acessar um recurso para o qual não tem permissões suficientes. Retorna uma
   * resposta com status HTTP 403 (FORBIDDEN) e uma mensagem de "Access Denied".
   *
   * @param exception A exceção {@link BadCredentialsException} capturada.
   * @return Um {@link ResponseEntity} com status 403 e mensagem "Access Denied".
   */
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentials(BadCredentialsException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body("Credenciais inválidas!");
  }

  /**
   * Manipula exceções do tipo {@link RuntimeException}. Retorna uma resposta com status HTTP 400
   * (BAD_REQUEST) e a mensagem da exceção.
   *
   * @param exception A exceção capturada.
   * @return A resposta com o status e mensagem da exceção.
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  /**
   * Manipula exceções genéricas do tipo {@link Exception}. Retorna uma resposta com status HTTP 500
   * (INTERNAL_SERVER_ERROR) e a mensagem da exceção.
   *
   * @param exception A exceção capturada.
   * @return A resposta com o status e mensagem da exceção.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exception.getMessage());
  }

  /**
   * Manipula exceções do tipo {@link Throwable}. Retorna uma resposta com status HTTP 502
   * (BAD_GATEWAY) e a mensagem da exceção.
   *
   * @param exception A exceção capturada.
   * @return A resposta com o status e mensagem da exceção.
   */
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<String> handleThrowable(Throwable exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_GATEWAY)
        .body(exception.getMessage());
  }
}

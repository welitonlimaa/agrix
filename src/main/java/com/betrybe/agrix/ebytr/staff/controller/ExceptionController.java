package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controlador exceções para lidar com exceções que podem ocorrer durante as execução da aplicação.
 */
@RestControllerAdvice
public class ExceptionController {

  /**
   * Trata a exceção ObjectNotFound,quando um objeto não é encontrado.
   *
   * @param exception A exceção NotFoundException lançada na aplicação.
   * @return Uma resposta com o status HTTP 404.
   */
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<Object> handleObjectNotFoundException(RuntimeException exception) {
    return ResponseEntity.status(404).body(exception.getMessage());
  }

  /**
   * Trata exceções genéricas Internal Server Error.
   *
   * @param exception A exceção Exception genérica lançada na aplicação.
   * @return Uma resposta com a mensagem de erro e o status HTTP 500.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
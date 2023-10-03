package com.agrix.staff.controller;

import com.agrix.staff.exception.ObjectNotFoundException;
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

}
package com.agrix.staff.exception;

/**
 * Exceção personalizada para indicar que um objeto não foi encontrado.
 */
public class ObjectNotFoundException extends RuntimeException {

  /**
   * Construtor.
   *
   * @param message A mensagem de erro detalhada.
   */
  public ObjectNotFoundException(String message) {
    super(message);
  }

  /**
   * Construtor.
   *
   * @param message A mensagem de erro detalhada.
   * @param cause   A causa da exceção.
   */
  public ObjectNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}

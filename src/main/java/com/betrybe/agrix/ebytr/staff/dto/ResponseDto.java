package com.betrybe.agrix.ebytr.staff.dto;

/**
 * Classe utilizada para representar uma resposta de API com dados e mensagem.
 */
public record ResponseDto(Object data, String message) {
  public ResponseDto(Object data) {
    this(data, null);
  }
}


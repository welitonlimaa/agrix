package com.betrybe.agrix.ebytr.staff.dto;

/**
 * Classe utilizada para representar um token de autenticação.
 */
public class TokenDto {

  private String token;

  public TokenDto(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

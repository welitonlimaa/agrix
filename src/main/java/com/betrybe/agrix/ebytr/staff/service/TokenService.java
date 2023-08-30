package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Serviço para geração e gerenciamento de tokens JWT.
 */
@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Gera um token JWT para a pessoa fornecida.
   *
   * @param person A pessoa para a qual o token será gerado.
   * @return O token JWT gerado.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("agrix")
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Gera a data de expiração do token (2 horas a partir do momento atual).
   *
   * @return A data de expiração do token.
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Valida um token JWT e retorna o assunto (nome de usuário) se o token for válido.
   *
   * @param token O token JWT a ser validado.
   * @return O assunto (nome de usuário) extraído do token válido.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("agrix")
        .build()
        .verify(token)
        .getSubject();
  }


}

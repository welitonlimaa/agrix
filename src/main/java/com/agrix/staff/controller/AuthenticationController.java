package com.agrix.staff.controller;

import com.agrix.staff.dto.AuthenticationDto;
import com.agrix.staff.dto.TokenDto;
import com.agrix.staff.entity.Person;
import com.agrix.staff.service.PersonService;
import com.agrix.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para autenticação e registro de usuários.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  /**
   * Construtor da classe {@code AuthenticationController}.
   *
   * @param authenticationManager Gerenciador de autenticação utilizado para autenticar os
   *                              usuários.
   * @param personService         Serviço que lida com a lógica relacionada às entidades de pessoa.
   * @param tokenService          Serviço responsável pela geração e manipulação de tokens de
   *                              autenticação.
   */
  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      PersonService personService, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.tokenService = tokenService;
  }

  /**
   * Endpoint para autenticação de um usuário.
   *
   * @param authenticationDto Os dados de autenticação (username e password) do usuário.
   * @return Uma resposta com o token JWT gerado.
   * @throws BadCredentialsException Se as credenciais do usuário forem inválidas.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto)
      throws BadCredentialsException {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authenticationDto.username(),
            authenticationDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    TokenDto response = new TokenDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}

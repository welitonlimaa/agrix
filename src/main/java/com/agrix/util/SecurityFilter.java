package com.agrix.util;

import com.agrix.staff.service.TokenService;
import com.agrix.staff.service.PersonService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro de segurança para autenticação de token JWT em cada requisição. Este filtro verifica se um
 * token JWT válido está presente no cabeçalho de autorização da requisição e, se estiver presente e
 * válido, configura a autenticação do usuário no contexto de segurança do Spring Security.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final PersonService personService;

  @Autowired
  public SecurityFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = recoveryToken(request);

    if (token != null) {
      String subject = tokenService.validateToken(token);
      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Recupera o token JWT do cabeçalho de autorização da requisição.
   *
   * @param request A requisição HTTP.
   * @return O token JWT, ou null se não estiver presente no cabeçalho.
   */
  private String recoveryToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      return null;
    }
    return authHeader.replace("Bearer ", "");
  }
}


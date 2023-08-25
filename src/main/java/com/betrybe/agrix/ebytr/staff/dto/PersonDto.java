package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe de transferência de dados (DTO) para informações de pessoa.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

  private Long id;
  private String username;
  private Role role;

  /**
   * Construtor que recebe um objeto Person e popula os atributos do DTO.
   *
   * @param person A pessoa da qual os dados serão copiados.
   */
  public PersonDto(Person person) {
    id = person.getId();
    username = person.getUsername();
    role = person.getRole();
  }
}

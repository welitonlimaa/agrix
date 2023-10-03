package com.agrix.staff.service;

import com.agrix.staff.exception.PersonNotFoundException;
import com.agrix.staff.repository.PersonRepository;
import com.agrix.staff.entity.Person;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Person Service.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Insere uma nova pessoa no banco de dados.
   *
   * @param person A pessoa a ser inserida.
   * @return A pessoa inserida.
   */
  public Person insert(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  /**
   * Carrega os detalhes de um usuário com o nome de usuário fornecido.
   *
   * @param username O nome de usuário do usuário a ser carregado.
   * @return Os detalhes do usuário carregado.
   * @throws UsernameNotFoundException Se o usuário com o nome de usuário fornecido não for
   *                                   encontrado.
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username);
  }

}

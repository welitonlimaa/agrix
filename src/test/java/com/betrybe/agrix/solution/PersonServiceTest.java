package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetPersonById() {
    Long personId = 1L;
    Person person = new Person();
    person.setId(personId);

    when(personRepository.findById(personId)).thenReturn(Optional.of(person));

    Person result = personService.getPersonById(personId);

    assertNotNull(result);
    assertEquals(personId, result.getId());
  }

  @Test
  void testGetPersonByIdNotFound() {
    Long personId = 1L;

    when(personRepository.findById(personId)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(personId));
  }

  @Test
  void testGetPersonByUsername() {
    String username = "yetson";
    Person person = new Person();
    person.setUsername(username);

    when(personRepository.findByUsername(username)).thenReturn(person);

    UserDetails result = personService.loadUserByUsername(username);

    assertNotNull(result);
    assertEquals(username, result.getUsername());
  }

  @Test
  void testCreatePerson() {
    String username = "yetson";
    String password = "12345678";
    Role role = Role.USER;
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);

    when(personRepository.save(person)).thenReturn(person);

    Person result = personService.insert(person);

    assertNotNull(result);
  }
}


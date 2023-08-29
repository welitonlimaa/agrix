package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por lidar com as requisições relacionadas a pessoas.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Cria uma nova pessoa e a salva no banco de dados.
   *
   * @param personData Os dados da pessoa a serem criados.
   * @return Os detalhes da pessoa criada com o status HTTP 201.
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody Person personData) {
    Person person = new Person();
    person.setUsername(personData.getUsername());
    person.setPassword(personData.getPassword());
    person.setRole(personData.getRole());

    Person createdPerson = personService.insert(person);

    PersonDto responseDto = new PersonDto();
    responseDto.setId(createdPerson.getId());
    responseDto.setUsername(createdPerson.getUsername());
    responseDto.setRole(createdPerson.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}

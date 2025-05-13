package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por lidar com requisições relacionadas a pessoas
 * (usuários).
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Construtor da classe PersonController.
   *
   * @param personService Serviço responsável pelas operações com a entidade
   *                      Person.
   */
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Endpoint para cadastrar uma nova pessoa no sistema.
   *
   * @param person Objeto Person recebido no corpo da requisição.
   * @return DTO da pessoa cadastrada.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto addPerson(@Valid @RequestBody Person person) {
    return PersonDto.fromEntity(personService.create(person));
  }
}

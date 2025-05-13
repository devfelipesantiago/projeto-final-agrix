package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * DTO (Data Transfer Object) utilizado para representar os dados públicos da entidade
 * {@link Person}.
 *
 * @param id       Identificador único da pessoa.
 * @param username Nome de usuário da pessoa.
 * @param role     Papel (perfil) da pessoa no sistema.
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * Converte uma entidade {@link Person} para um DTO {@link PersonDto}.
   *
   * @param person Entidade Person a ser convertida.
   * @return Instância de PersonDto com os dados da entidade.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}

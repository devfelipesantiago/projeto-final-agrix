package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para a entidade {@link Person}.
 * Esta interface permite operações CRUD básicas e consultas personalizadas
 * relacionadas à entidade {@link Person} no banco de dados.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  /**
   * Busca uma pessoa pelo nome de usuário.
   *
   * @param username O nome de usuário da pessoa a ser encontrada.
   * @return Um {@link Optional} contendo a pessoa, caso encontrada.
   */
  Optional<Person> findByUsername(String username);
}
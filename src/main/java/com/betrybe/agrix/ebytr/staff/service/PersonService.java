package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para lidar com a lógica de negócios relacionada às pessoas. Contém métodos para
 * acessar, criar e manipular dados de pessoas.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  /**
   * Construtor para inicializar o repositório de pessoas.
   *
   * @param personRepository O repositório usado para acessar dados de pessoas.
   */
  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Retorna uma pessoa com o ID especificado.
   *
   * @param id O ID da pessoa a ser recuperada.
   * @return A pessoa com o ID especificado.
   * @throws PersonNotFoundException Se a pessoa não for encontrada.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Retorna uma pessoa com o nome de usuário especificado.
   *
   * @param username O nome de usuário da pessoa a ser recuperada.
   * @return A pessoa com o nome de usuário especificado.
   * @throws PersonNotFoundException Se a pessoa não for encontrada.
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> person = personRepository.findByUsername(username);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Cria uma nova pessoa no sistema.
   *
   * @param person A pessoa a ser criada.
   * @return A pessoa criada.
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);
    return personRepository.save(person);
  }

  /**
   * Locates the user based on the username. In the actual implementation, the search may possibly
   * be case sensitive, or case insensitive depending on how the implementation instance is
   * configured. In this case, the <code>UserDetails</code> object that comes back may have a
   * username that is of a different case than what was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}

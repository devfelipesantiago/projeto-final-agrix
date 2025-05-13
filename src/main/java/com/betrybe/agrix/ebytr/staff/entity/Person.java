package com.betrybe.agrix.ebytr.staff.entity;

import com.betrybe.agrix.ebytr.staff.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class representing a person entity in the system. This class is used to store user authentication
 * and role information.
 */
@Entity
public class Person implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * Default constructor for Person.
   */
  public Person() {
  }

  /**
   * Default constructor for Person.
   */
  public Person(Long id, String username, String password, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  /**
   * Gets the ID of the person.
   *
   * @return the person's ID.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the ID of the person.
   *
   * @param id the new ID.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the username of the person.
   *
   * @return the username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Indicates whether the user's account has expired. An expired account cannot be authenticated.
   *
   * @return <code>true</code> if the user's account is valid (ie non-expired)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
   *
   * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) has expired. Expired credentials prevent
   * authentication.
   *
   * @return <code>true</code> if the user's credentials are valid (ie non-expired)
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
   *
   * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Sets the username of the person.
   *
   * @param username the new username.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password of the person.
   *
   * @return the password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the person.
   *
   * @param password the new password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the role of the person.
   *
   * @return the role.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets the role of the person.
   *
   * @param role the new role.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Checks if two Person objects are equal.
   *
   * @param o the object to compare.
   * @return true if equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(username,
        person.username) && Objects.equals(password, person.password)
        && Objects.equals(role, person.role);
  }

  /**
   * Returns the authorities granted to the user. Cannot return <code>null</code>.
   *
   * @return the authorities, sorted by natural key (never <code>null</code>)
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println(role.getName());
    return List.of(new SimpleGrantedAuthority(role.getName()));
  }
}
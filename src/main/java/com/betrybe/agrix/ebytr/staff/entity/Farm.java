package com.betrybe.agrix.ebytr.staff.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Representa uma fazenda no sistema.
 * Esta entidade mapeia a tabela {@code farms} no banco de dados e contém informações sobre o nome
 * da fazenda, seu tamanho e as culturas ({@link Crop}) associadas a ela.
 */
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  @JsonManagedReference
  private List<Crop> crops;

  /**
   * Construtor para criar uma instância de {@link Farm}.
   *
   * @param id   O identificador único da fazenda.
   * @param name O nome da fazenda.
   * @param size O tamanho da fazenda em hectares.
   */
  public Farm(Integer id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  /**
   * Construtor padrão para a entidade {@link Farm}.
   */
  public Farm() {
  }

  /**
   * Obtém o identificador único da fazenda.
   *
   * @return O ID da fazenda.
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Define o identificador único da fazenda.
   *
   * @param id O ID da fazenda.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Obtém o nome da fazenda.
   *
   * @return O nome da fazenda.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Define o nome da fazenda.
   *
   * @param name O nome da fazenda.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Obtém o tamanho da fazenda em hectares.
   *
   * @return O tamanho da fazenda.
   */
  public Double getSize() {
    return this.size;
  }

  /**
   * Define o tamanho da fazenda em hectares.
   *
   * @param size O tamanho da fazenda.
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * Obtém a lista de culturas associadas à fazenda.
   *
   * @return A lista de culturas ({@link Crop}) associadas à fazenda.
   */
  public List<Crop> getCrops() {
    return this.crops;
  }

  /**
   * Define a lista de culturas associadas à fazenda.
   *
   * @param crops A lista de culturas ({@link Crop}) a ser associada à fazenda.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}

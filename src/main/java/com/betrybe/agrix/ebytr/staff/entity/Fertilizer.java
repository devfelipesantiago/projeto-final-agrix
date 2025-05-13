package com.betrybe.agrix.ebytr.staff.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um fertilizante no sistema.
 */
@Entity
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String brand;
  private String composition;

  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops = new ArrayList<>();

  /**
   * Construtor da classe Fertilizer.
   *
   * @param id          Identificador único do fertilizante.
   * @param name        Nome do fertilizante.
   * @param brand       Marca do fertilizante.
   * @param composition Composição química do fertilizante.
   */
  public Fertilizer(Integer id, String name, String brand, String composition) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  /**
   * Construtor padrão da classe Fertilizer.
   */
  public Fertilizer() {
  }

  /**
   * Obtém o ID do fertilizante.
   *
   * @return O ID do fertilizante.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o ID do fertilizante.
   *
   * @param id O novo ID do fertilizante.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Obtém o nome do fertilizante.
   *
   * @return O nome do fertilizante.
   */
  public String getName() {
    return name;
  }

  /**
   * Define o nome do fertilizante.
   *
   * @param name O novo nome do fertilizante.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Obtém a marca do fertilizante.
   *
   * @return A marca do fertilizante.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Define a marca do fertilizante.
   *
   * @param brand A nova marca do fertilizante.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Obtém a composição do fertilizante.
   *
   * @return A composição química do fertilizante.
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Define a composição do fertilizante.
   *
   * @param composition A nova composição química do fertilizante.
   */
  public void setComposition(String composition) {
    this.composition = composition;
  }

  /**
   * Obtém a lista de plantações que utilizam este fertilizante.
   *
   * @return Lista de {@link Crop} associadas ao fertilizante.
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Define a lista de plantações que utilizam este fertilizante.
   *
   * @param crops A nova lista de plantações associadas ao fertilizante.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}

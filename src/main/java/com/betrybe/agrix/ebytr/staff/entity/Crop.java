package com.betrybe.agrix.ebytr.staff.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Representa uma cultura agrícola (Crop) associada a uma fazenda.
 */
@Entity
@Table(name = "crop")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  @JsonBackReference
  private Farm farm;

  private Double plantedArea;
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate plantedDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate harvestDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private List<Fertilizer> fertilizers;

  /**
   * Construtor para criar uma instância de {@link Crop}.
   *
   * @param id          O identificador único da cultura.
   * @param farm        A fazenda ({@link Farm}) associada à cultura.
   * @param name        O nome da cultura plantada.
   * @param plantedArea A área plantada da cultura, em hectares.
   */
  public Crop(Integer id, Farm farm, String name, Double plantedArea, LocalDate plantedDate,
      LocalDate harvestDate) {
    this.id = id;
    this.farm = farm;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  /**
   * Construtor vazio da classe Crop. Este construtor é necessário para o JPA (Hibernate).
   */
  public Crop() {
  }

  /**
   * Obtém o ID da plantação.
   *
   * @return O ID da plantação.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o ID da plantação.
   *
   * @param id O ID da plantação.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Obtém a fazenda associada à plantação.
   *
   * @return A fazenda onde a plantação está localizada.
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Define a fazenda associada à plantação.
   *
   * @param farm A fazenda onde a plantação será associada.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  /**
   * Obtém o nome da plantação.
   *
   * @return O nome da plantação.
   */
  public String getName() {
    return name;
  }

  /**
   * Define o nome da plantação.
   *
   * @param name O nome da plantação.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Obtém a área plantada da plantação.
   *
   * @return A área plantada em hectares.
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Define a área plantada da plantação.
   *
   * @param plantedArea A área plantada em hectares.
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Obtém a data de plantio da plantação.
   *
   * @return A data de plantio.
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Define a data de plantio da plantação.
   *
   * @param plantedDate A data de plantio.
   */
  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  /**
   * Obtém a data de colheita da plantação.
   *
   * @return A data de colheita.
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  /**
   * Define a data de colheita da plantação.
   *
   * @param harvestDate A data de colheita.
   */
  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  /**
   * Obtém a lista de fertilizantes associados à plantação.
   *
   * @return A lista de fertilizantes utilizados na plantação.
   */
  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  /**
   * Adiciona um fertilizante à lista de fertilizantes da plantação.
   *
   * @param fertilizers O fertilizante a ser adicionado.
   */
  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}
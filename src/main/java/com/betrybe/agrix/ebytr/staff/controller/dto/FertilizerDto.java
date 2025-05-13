package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * DTO (Data Transfer Object) para representar um fertilizante.
 *
 * @param id          O ID do fertilizante.
 * @param name        O nome do fertilizante.
 * @param brand       A marca do fertilizante.
 * @param composition A composição do fertilizante.
 */
public record FertilizerDto(int id, String name, String brand, String composition) {

  /**
   * Converte uma entidade {@link Fertilizer} para um {@link FertilizerDto}.
   *
   * @param fertilizer A entidade {@link Fertilizer} a ser convertida.
   * @return Uma instância de {@link FertilizerDto} representando o fertilizante.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}


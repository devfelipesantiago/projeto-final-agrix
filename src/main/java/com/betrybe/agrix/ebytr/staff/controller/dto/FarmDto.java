package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * Data Transfer Object (DTO) para a entidade Farm.
 * Este DTO é usado para transferir dados relacionados a uma fazenda entre as
 * camadas da aplicação.
 */
public record FarmDto(Integer id, String name, Double size) {

  /**
   * Converte uma entidade Farm para um DTO FarmDto.
   *
   * @param farms A entidade Farm a ser convertida.
   * @return Um novo objeto FarmDto com os dados da entidade Farm.
   */
  public static FarmDto fromEntity(Farm farms) {
    return new FarmDto(
        farms.getId(),
        farms.getName(),
        farms.getSize());
  }
}
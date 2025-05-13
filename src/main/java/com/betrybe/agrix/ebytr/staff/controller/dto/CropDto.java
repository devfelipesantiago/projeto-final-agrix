package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para representar informações de uma cultura
 * agrícola ({@link Crop}).
 */
public record CropDto(Integer id, String name, Double plantedArea, Integer farmId,
                      LocalDate plantedDate, LocalDate harvestDate) {

  /**
   * Cria uma instância de {@link CropDto} a partir de uma entidade {@link Crop}.
   *
   * @param crop A entidade {@link Crop} que contém os dados da cultura.
   * @return Uma instância de {@link CropDto} com os dados da cultura.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }
}
package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para operações relacionadas a plantações (Crops). Este controlador oferece
 * endpoints para recuperar informações sobre as plantações.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Construtor da classe CropController.
   *
   * @param cropService O serviço de plantações a ser injetado.
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Retorna uma lista de todas as plantações. Este método mapeia as entidades de plantação para
   * objetos CropDto antes de retornar a lista.
   *
   * @return Uma lista de CropDto representando todas as plantações.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
  public List<Crop> allCrops() throws CropNotFoundException {
    return cropService.getAllCrops();
  }

  /**
   * Retorna uma plantação específica pelo ID.
   *
   * @param cropId O ID da plantação a ser recuperada.
   * @return CropDto representando a plantação encontrada.
   * @throws CropNotFoundException Se a plantação não for encontrada.
   */
  @GetMapping("/{cropId}")
  @ResponseStatus(HttpStatus.OK)
  public CropDto cropById(@PathVariable Integer cropId) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.findById(cropId));
  }

  /**
   * Retorna uma lista de plantações cuja data de colheita está entre as datas informadas.
   *
   * @param start Data inicial do intervalo de colheita.
   * @param end   Data final do intervalo de colheita.
   * @return Lista de {@link CropDto} representando as plantações encontradas.
   */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> cropByPlantedDateBetweenHarvestDate(
      @RequestParam(name = "start") LocalDate start,
      @RequestParam(name = "end") LocalDate end) {
    return cropService.getAllCropByPlantedDateBetweenHarvestDate(start, end)
        .stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Adiciona um fertilizante a uma plantação específica.
   *
   * @param cropId       O ID da plantação à qual o fertilizante será associado.
   * @param fertilizerId O ID do fertilizante a ser adicionado.
   * @return Uma mensagem indicando o sucesso da operação.
   * @throws CropNotFoundException       Se a plantação não for encontrada.
   * @throws FertilizerNotFoundException Se o fertilizante não for encontrado.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizerWithCrop(@PathVariable Integer cropId, @PathVariable Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    return cropService.saveFertilizerWithCrop(cropId, fertilizerId);
  }

  /**
   * Retrieves fertilizers associated with a specific crop.
   *
   * @param cropId The ID of the crop
   * @return The crop with its associated fertilizers
   * @throws CropNotFoundException If no crop with the given ID exists
   */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Crop> fertilizersByCropId(@PathVariable Integer cropId)
      throws CropNotFoundException {
    return cropService.findFertilizerByCropId(cropId);
  }

}